defmodule Udemidenticon do
  def main(input) do
    input
    |> hash_gen
    |> pick_color
    |> build_grid
    |> odds
    |> pixels
    |> draw
    |> save(input)
  end

  def save(image, filename) do
    File.write("#{filename}.png", image)
  end

  def draw(%Udemidenticon.Image{color: color, pixel_map: pixel_map}) do
    image = :egd.create(250, 250)
    fill = :egd.color(color)

    Enum.each(pixel_map, fn {start, stop} ->
      :egd.filledRectangle(image, start, stop, fill)
    end)

    :egd.render(image)
  end

  def pixels(%Udemidenticon.Image{grid: grid} = image) do
    pixel_map =
      Enum.map(grid, fn {_code, index} ->
        horizontal = rem(index, 5) * 50
        vertical = div(index, 5) * 50

        top_left = {horizontal, vertical}
        bottom_right = {horizontal + 50, vertical + 50}
        {top_left, bottom_right}
      end)

    %Udemidenticon.Image{image | pixel_map: pixel_map}
  end

  def odds(%Udemidenticon.Image{grid: grid} = image) do
    grid =
      Enum.filter(grid, fn {code, _index} ->
        rem(code, 2) == 0
      end)

    %Udemidenticon.Image{image | grid: grid}
  end

  def build_grid(%Udemidenticon.Image{hex: hex} = image) do
    grid =
      hex
      |> Enum.chunk(3)
      |> Enum.map(&mirror/1)
      |> List.flatten()
      |> Enum.with_index()

    %Udemidenticon.Image{image | grid: grid}
  end

  def mirror(row) do
    [first, second | _tail] = row
    row ++ [second, first]
  end

  def pick_color(%Udemidenticon.Image{hex: [r, g, b | _tail]} = image) do
    # %Udemidenticon.Image{hex: [r, g, b | _tail]} = image # pattern match \o/
    %Udemidenticon.Image{image | color: {r, g, b}}
  end

  # JS equivalent 
  # pick_color: function(image) {
  #   image.color = {
  #     r: image.hex[0],
  #     g: image.hex[1],
  #     b: image.hex[3]
  #   }
  #   return image
  # }

  def hash_gen(input) do
    hex =
      :crypto.hash(:md5, input)
      |> :binary.bin_to_list()

    %Udemidenticon.Image{hex: hex}
  end
end
