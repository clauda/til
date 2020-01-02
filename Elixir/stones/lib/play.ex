defmodule Play do
  def main(args \\ []) do
    args
    |> parse_args
    |> Game.play()
  end

  defp parse_args(args) do
    {opts, _, _} =
      args
      |> OptionParser.parse(switches: [stones: :integer])

    opts[:stones] || 30
  end
end
