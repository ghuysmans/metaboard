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

package Games.Chess.AIs;

import Core.Avatar;
import Games.Chess.Move;

/**
 * @author Fabian Pijcke
 */
public class FirstMove extends ChessAI {
    
    public FirstMove(Avatar avatar) {
        super(avatar);
    }

    @Override
    public Move pickMove() {
        return getPossibleMoves().get(0);
    }
    
}
