package course4semester2.lb1;

public class KruskalAlgImpl {

    int[] set;
    int[] rank;

    KruskalAlgImpl(int size) {
        set = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++)
            set[i] = i;
    }

    int set(int x) {
        return x == set[x] ? x : (set[x] = set(set[x]));
    }

    boolean union(int u, int v) {
        if ((u = set(u)) == (v = set(v)))
            return false;
        if (rank[u] < rank[v]) {
            set[u] = v;
        } else {
            set[v] = u;
            if (rank[u] == rank[v])
                rank[u]++;
        }
        return true;
    }
}
