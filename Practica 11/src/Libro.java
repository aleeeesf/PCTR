public class Libro implements java.io.Serializable
{
    private String titulo, autor, editorial;
    private int publicacion;

	public Libro() {}
	public Libro(String titulo, String autor, String editorial, int publicacion)
    {
		this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.publicacion = publicacion;
	}
	public String Titulo(){return titulo;}	
	public String Autor() {return autor;}
    public String Editorial(){return editorial;}
    public int Publicacion() {return publicacion;}

    public void setTitulo(String Titulo_){this.titulo = Titulo_;}
    public void setAutor(String Autor_) {this.autor = Autor_;}
    public void setEditorial(String Editorial_){this.editorial = Editorial_;}
    public void setPublicacion(int Publicacion_) {this.publicacion = Publicacion_;}

	@Override
	public String toString()
	{	return "Libro con titulo ="+titulo +", autor = " + autor + " editorial = "+ editorial+" publicacion = "+publicacion;}
}