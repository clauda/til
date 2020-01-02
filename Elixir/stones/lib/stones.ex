defmodule Stones do
  @moduledoc """
  Game of Stones.
  """

  @doc """
  Starts a new game.
  """
  def start(stones) do
    spawn(fn -> listen({1, stones}) end)
    |> Process.register(:game)
  end

  defp listen({nil, 0}), do: listen({1, 40})

  defp listen({player, stones} = state) do
    state =
      receive do
        {:info, sender} ->
          notice(sender, state)

        {:take, sender, num_stones} ->
          turn({sender, player, num_stones, stones})

        _ ->
          state
      end

    state |> listen
  end

  defp notice(sender, state) do
    send(sender, state)
    state
  end

  defp turn({sender, player, stones_taken, stones_count})
       when not is_integer(stones_taken) or
              stones_taken < 1 or
              stones_taken > 3 or
              stones_taken > stones_count do
    send(sender, {:error, "You can take from 1 to 3 stones, and
    this number cannot exceed the total count of stones in the pile!"})

    {player, stones_count}
  end

  defp turn({sender, player, stones_taken, stones_count})
       when stones_taken == stones_count do
    send(sender, {:winner, next(player)})

    {nil, 0}
  end

  defp turn({sender, player, stones_taken, stones_count}) do
    next_player = next(player)
    stones_balance = stones_count - stones_taken
    send(sender, {:next_turn, next_player, stones_balance})

    {next_player, stones_balance}
  end

  defp next(1), do: 2
  defp next(2), do: 1
end
