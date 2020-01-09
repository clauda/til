
defmodule Game.Client do
  def play(stones \\ 30) do
    Game.Server.start(stones)
    start!()
  end

  defp start! do
    case Game.Server.stats() do
      {player, stones} ->
        IO.puts("Starting the game! It's player #{player} turn.
        There are #{stones} stones in the pile.")
    end

    run()
  end

  defp take({count, _}), do: count
  defp take(:error), do: 0

  def ask do
    IO.gets("\nPlease take from 1 to 3 stones:\n")
    |> String.trim()
    |> Integer.parse()
    |> take()
  end

  defp run do
    case Game.Server.take(ask()) do
      {:next_turn, player, stones} ->
        IO.puts("\nPlayer #{player} turns next. Stones: #{stones}")
        run()

      {:winner, winner} ->
        IO.puts("\nPlayer #{winner} wins!!!")

      {:error, reason} ->
        IO.puts("\nThere was an error: #{reason}")
        run()
    end
  end
end

# Game.Client.play(10)
