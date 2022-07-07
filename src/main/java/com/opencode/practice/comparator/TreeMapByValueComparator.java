package com.opencode.practice.comparator;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@Component
public class TreeMapByValueComparator {
    public <K, V extends Comparable<V> > TreeMap<K, V>
    valueSort(final TreeMap<K, V> map)
    {
        Comparator<K> valueComparator = new Comparator<K>()
        {
            public int compare(K k1, K k2)
            {
                int comp = map.get(k1).compareTo(map.get(k2));
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
}
