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
 * Games use boards of various shapes and sizes. Some common board classes are
 * factored in this package.
 * 
 * A board contains all the elements visible to all of the players involved in a
 * game. A player can interract with the board using moves but can not edit it
 * directly, while the game engine can. For this reason, the design pattern
 * Proxy is used. This explains the presence of two interfaces about boards:
 * IBoard, which is an editable board, and IBoardProxy, which typically
 * encapsulates a board and shares only the accessors and hides the mutators.
 * 
 * Coordinates are tightly related to boards and are therefore defined in this
 * package and its subpackages.
 * 
 * Note that if a game uses a very unique board, it should not be defined in
 * this package. We expect board classes as Grid, Path, and Graph to be defined
 * here.
 * 
 * Classes defined here should not define anything related to the user interface
 * (graphical- or console-oriented).
 */
package Board;
