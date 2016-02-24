package Games.Nim;

import Board.Path.IPath;
import Board.Path.PathCoordinate;

public interface IBoard extends IPath<Token, PathCoordinate> {
	PathCoordinate getTokenPosition();
}
