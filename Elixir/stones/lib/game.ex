defmodule Game do
  def play(stones \\ 30) do
    Stones.start(stones)
    start!()
  end

  defp start! do
    send(:game, {:info, self()})

    receive do
      {player, stones} ->
        IO.puts("Starting the game! It's player #{player} turn.
        There are #{stones} stones in the pile.")
    end

    run()
  end

  defp take({count, _}), do: count
  defp take(:error), do: nil

  def ask do
    IO.gets("\nPlease take from 1 to 3 stones:\n")
    |> String.trim()
    |> Integer.parse()
    |> take()
  end

  defp run do
    send(:game, {:take, self(), ask()})

    receive do
      {:next_turn, player, stones} ->
        IO.puts("\nPlayer #{player} turns next. Stones: #{stones}")
        run()

      {:winner, winner} ->
        IO.puts("\nPlayer #{winner} wins!!!")

      {:error, reason} ->
        IO.puts("\nThere was an error: #{reason}")
        run()
    after
      5000 -> IO.puts(:stderr, "Server timeout.")
    end
  end
end
