package comparador;

import java.util.Comparator;
import java.util.TreeMap;

public class Organizar {
	public static <K, V extends Comparable<V>> TreeMap<K, V>

			valueSort(TreeMap<K, V> map) {

		Comparator<K> valueComparator = new Comparator<K>() {

			public int compare(K k1, K k2) {

				int comp = map.get(k2).compareTo(map.get(k1));

				if (comp == 0)
					return 1;

				else
					return comp;
			}
		};

		TreeMap<K, V> sorted = new TreeMap<K, V>(valueComparator);

		sorted.putAll(map);

		return sorted;
	}

	public static <K, V extends Comparable<V>> TreeMap<K, V>

			valueSortInverse(TreeMap<K, V> map1) {

		Comparator<K> valueComparator = new Comparator<K>() {

			public int compare(K k1, K k2) {

				int comp = map1.get(k2).compareTo(map1.get(k1));

				if (comp == 0)
					return 0;

				else
					return comp;
			}
		};

		TreeMap<K, V> sorted = new TreeMap<K, V>(valueComparator);

		sorted.putAll(map1);

		return sorted;
	}

}
