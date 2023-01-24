package ch4.item20;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractMapEntry <K,V> implements Map.Entry<K,V> {
    // 변경 가능한 엔트리는 이 메서드를 반드시 재정의
    @Override
    public int hashCode() {
        return Objects.hashCode(getKey())^Objects.hashCode(getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Map.Entry<?,?>))
            return false;
        Map.Entry<?,?> e = (Map.Entry) obj;
        return Objects.equals(e.getKey(), getKey()) && Objects.equals(e.getValue(), getValue());
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }

    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }
}
