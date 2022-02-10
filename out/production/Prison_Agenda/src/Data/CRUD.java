package Data;

import java.util.ArrayList;

public interface CRUD<T> {
	void add(T data, ArrayList<T> dataList);

	void read(T data, ArrayList<T> dataList);

	void update(T data, ArrayList<T> dataList);

	void destroy(T data, ArrayList<T> dataList);
}
