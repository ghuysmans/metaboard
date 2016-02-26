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

package Utils;

import java.util.function.Predicate;

/**
 * An extended Consumer allowing a filter method which will cause the consumer to be applied only to the elements
 * accepted by the filter.
 * 
 * @author shepard
 *
 * @param <T>
 */
public interface IConsumer<T> extends java.util.function.Consumer<T> {
	/**
	 * @param p any predicate on a T instance.
	 * @return a new consumer applying only to instances satisfying p.
	 */
	default IConsumer<T> filter(Predicate<T> p) {
		IConsumer<T> con = this;
		
		return new IConsumer<T>() {
			@Override
			public void accept(T t) {
				if (p.test(t)) {
					con.accept(t);
				}
			}
		};
	}
}
