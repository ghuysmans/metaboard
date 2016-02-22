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
public interface Consumer<T> extends java.util.function.Consumer<T> {
	/**
	 * @param p any predicate on a T instance.
	 * @return a new consumer applying only to instances satisfying p.
	 */
	default Consumer<T> filter(Predicate<T> p) {
		Consumer<T> con = this;
		
		return new Consumer<T>() {
			@Override
			public void accept(T t) {
				if (p.test(t)) {
					con.accept(t);
				}
			}
		};
	}
}
