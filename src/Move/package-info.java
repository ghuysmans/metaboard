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
 * Most games use a standard move model. Either the movement model (Chess,
 * Stratego) or the placement model (Go, Dominoes, OXO).
 * 
 * These two move models are factored here. More specialized move models can be
 * implemented by extending the Core.IMove interface.
 * 
 * An alternative to move models is to provide the player with a list of allowed
 * moves. This way one has not to bother explaining rules, just to compute the
 * possibilites in a given board state. This behaviour is captured by the
 * package Move.Picking.
 */
package Move;