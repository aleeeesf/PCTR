
public class Heterogenea {
	public static int n,m;	
	public synchronized void incrementar_n() {n++;}
	public synchronized void decrementar_n() {n--;}
	public void incrementar_m() {m++;}
	public void decrementar_m() {m--;}
	public synchronized int obtener_n() {return n;}
	public synchronized int obtener_m() {return m;}
}
