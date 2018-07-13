package br.dcc.ufba.mata63.balaiolivros.backend.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Representa um livro cadastrado na plataforma.
 */
public class LivroModel implements Serializable {

    /* Id deste objeto */
    private Long id = null;
    /* Nome do livro */
    private String nome;
    /* Data de inserção*/
    private LocalDate date;
    /* Categoria */
    private CategoriaModel category;
    /* Contador de exemplares */
    private int count;
    /* Peso do livro */
    private double peso;
    /* ISBN (Cadastro Único) do livro */
    private String ISBN;
    /* Dimensões do livro */
    private double altura;
    private double largura;
    private double profundidade;
    /* Número de páginas do livro */
    private int npaginas;
    /* Idioma do livro */
    private String idioma;
    /* Acabamento (como é feito) do livro */
    private String acabamento;
    /* Número ou definição da edição */
    private String edicao;
    /* Ano da edição */
    private int anoedicao;
    /* País de origem do Livro */
    private String paisorigem;
    /* Editora do Livro */
    private String editora;
    /* Autor ou autores do livro */
    private String autor;

    /**
     * Construtor base
     */
    public LivroModel() {
        resetPriv();
    }

    /**
     * Constroi uma nova instância com os dados informados
     *
     * @param nome
     *          Nome do Livro
     * @param category
     *          Categoria do Livro
     * @param peso
     *          Peso do livro
     * @param ISBN
     *          ISBN (cadastro único) do livro
     * @param altura
     *          Altura do livro
     * @param largura
     *          Largura do livro
     * @param profundidade
     *          Profundidade do livro
     * @param npaginas
     *          Número de páginas
     * @param idioma
     *          Idioma do livro
     * @param acabamento
     *          Acabamento do livro
     * @param edicao
     *          Numero ou definição da edição
     * @param anoedicao
     *          Ano da edição
     * @param paisorigem
     *          País de origem do livro
     * @param editora
     *          Editora do livro
     * @param autor
     *          Autor do livro
     * 
     */
    public LivroModel(String nome,
        CategoriaModel category,
        double peso,
        String ISBN,
        double altura,
        double largura,
        double profundidade,
        int npaginas,
        String idioma,
        String acabamento,
        String edicao,
        int anoedicao,
        String paisorigem,
        String editora,
        String autor) {
    
        this.nome = nome;
        this.category = new CategoriaModel(category);
        
        this.peso = peso;
        this.ISBN = ISBN;
        this.altura = altura;
        this.largura = largura;
        this.profundidade = profundidade;
        this.npaginas = npaginas;
        this.idioma = idioma;
        this.acabamento = acabamento;
        this.edicao = edicao;
        this.anoedicao = anoedicao;
        this.paisorigem = paisorigem;
        this.editora = editora;
        this.autor = autor;
        
        this.date = LocalDate.now();
        this.count = 1;
    }

    /**
     * Copy constructor.
     *
     * @param other
     *            The instance to copy
     */
    public LivroModel(LivroModel other) {
        this(other.getNome(), 
                other.getCategory(),
                other.getPeso(),
                other.getISBN(),
                other.getAltura(),
                other.getLargura(),
                other.getProfundidade(),
                other.getNpaginas(),
                other.getIdioma(),
                other.getAcabamento(),
                other.getEdicao(),
                other.getAnoedicao(),
                other.getPaisorigem(),
                other.getEditora(),
                other.getAutor());
        this.id = other.getId();
        this.date = other.getDate();
        this.count = other.getCount();
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(double profundidade) {
        this.profundidade = profundidade;
    }

    public int getNpaginas() {
        return npaginas;
    }

    public void setNpaginas(int npaginas) {
        this.npaginas = npaginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getAcabamento() {
        return acabamento;
    }

    public void setAcabamento(String acabamento) {
        this.acabamento = acabamento;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public int getAnoedicao() {
        return anoedicao;
    }

    public void setAnoedicao(int anoedicao) {
        this.anoedicao = anoedicao;
    }

    public String getPaisorigem() {
        return paisorigem;
    }

    public void setPaisorigem(String paisorigem) {
        this.paisorigem = paisorigem;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Resets all fields to their default values.
     */
    private void resetPriv() {
        this.id = null;
        this.nome = "";
        this.date = LocalDate.now();
        this.category = null;
        this.count = 0;
    }

    /**
     * Resets all fields to their default values.
     */
    public void reset(){
        resetPriv();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the value of nome
     *
     * @return the value of nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of nome
     *
     * @param nome
     *            new value of nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the value of category
     *
     * @return the value of category
     */
    public CategoriaModel getCategory() {
        return category;
    }

    /**
     * Sets the value of category
     *
     * @param category
     *            new value of category
     */
    public void setCategory(CategoriaModel category) {
        this.category = category;
    }

    /**
     * Gets the value of date
     *
     * @return the value of date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the value of date
     *
     * @param date
     *            new value of date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the value of count
     *
     * @return the value of count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the value of count
     *
     * @param count
     *            new value of count
     */
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        // Must use getters instead of direct member access,
        // to make it work with proxy objects generated by the view model
        return "Livro{" + "id=" + getId() + ", nome=\""
                + getNome() + "\", category=\"" + getCategory() + "\", date=\""
                + getDate() + ", count=" + getCount() + ", peso=\"" + getPeso()
                + "\", ISBN=\"" + getISBN() + "\", altura=\"" + getAltura() 
                + "\", altura=\"" + getLargura() + "\", profundidade=\"" + getProfundidade()
                + "\", npaginas=\"" + getNpaginas() + "\", idioma=\"" + getIdioma()
                + "\", acabamento=\"" + getAcabamento() + "\", edicao=\"" + getEdicao()
                + "\", anoedicao=\"" + getAnoedicao() + "\", paisorigm=\"" +getPaisorigem()
                + "\", editora=\"" + getEditora() + "\", autor=\"" + getAutor() + "\"}";
    }

}
