/*
 Copyright 2015-2016 Fabian Pijcke

 This file is part of MetaBoard.

 MetaBoard is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 MetaBoard is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with MetaBoard. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * A player is anything that interracts with the game using legal moves. In
 * particular, it can be a human (working through some UI) or an AI.
 * 
 * The Games.Nim.FXLauncher class will load only the AIs listed in the
 * Games.Nim.Players.PlayersList class. Note that HumanConsole is not listed
 * here as it does not cope well with standard Java IDEs.
 */
package Games.Nim.Players;