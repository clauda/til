defmodule StonesTest do
  use ExUnit.Case
  doctest Stones

  @tag :skip
  test "greets the world" do
    assert Stones.start() == :world
  end
end
