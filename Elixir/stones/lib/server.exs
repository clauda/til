defmodule Stones.GameServer do
  @moduledoc """
  Game of Stones version using GenServer.
  """
  use GenServer
  @server __MODULE__

  def start(stones \\ 30) do
    GenServer.start(@server, stones, name: @server)
  end

  def init(stones) do
    {:ok, {1, stones}}
  end

  def stats do
    GenServer.call(@server, :stats)
  end

  def take(stones) do
    GenServer.call(@server, {:take, stones})
  end

  def handle_call(:stats, _, stones) do
    {:reply, stones, stones}
  end

  def handle_call({:take, stones_taken}, _, {player, stones}) do
    turn({player, stones_taken, stones})
  end

  def terminate(_, _) do
    IO.puts("bye!")
  end

  defp turn({player, stones_taken, stones})
       when not is_integer(stones_taken) or
              stones_taken < 1 or
              stones_taken > 3 or
              stones_taken > stones do
    {
      :reply,
      {:error,
       "You can take from 1 to 3 stones, and this number cannot \n
       exceed the total count of stones in the pile!"},
      {player, stones}
    }
  end

  defp turn({player, stones_taken, stones_count})
       when stones_taken == stones_count do
    {:stop, :normal, {:winner, next(player), {nil, 0}}}
  end

  defp turn({player, stones_taken, stones_count}) do
    next_player = next(player)
    stones_balance = stones_count - stones_taken
    {:reply, {:next_turn, next_player, stones_balance}, {next_player, stones_balance}}
  end

  defp next(1), do: 2
  defp next(2), do: 1
end

defmodule Stones.GameClient do
  def play(stones \\ 30) do
    Stones.GameServer.start(stones)
    start!()
  end

  defp start! do
    case Stones.GameServer.stats do
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
    case Stones.GameServer.take(ask()) do
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


# Usage: $ elixir server.exs
Stones.GameClient.play(10)
