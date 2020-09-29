import java.util.ArrayList;

public class Data implements Comparable<Data>{
    private String categoria;        //La variabile rappresenta la categoria associata al dato
    private ArrayList<String> amici;    //Lista di amici che hanno messo like
    private String creatore;        //creatore del dato
    private String testo;           //contenuto di tipo testuale
    private int n_likes;            //numero dei like
    //Utilizzato nella seconda implementazione
    private ArrayList<String> liked = new ArrayList<>();

    public Data() {
        this.liked=new ArrayList<String>();
    }


    public Data (String creatore, String testo, String categoria) throws NullPointerException{
        Check.checkName(creatore);
        Check.checkText(testo);
        this.categoria = categoria;
        this.amici = new ArrayList<String>();
        this.creatore = creatore;
        this.testo = testo;
        this.n_likes = 0;

    }

    public Data (Data d) throws NullPointerException{
        this.categoria = d.getCategory();
        this.creatore = d.getCreatore();
        this.testo = d.getTesto();
        this.n_likes = d.getN_likes();
        this.amici = d.getAmici();
    }

    public Data copyData(){
        return new Data(this);
    }

    //Implementazione metodi get
    public String getCategory(){
        return this.categoria;
    }

    private ArrayList<String> getAmici(){
        return this.amici;
    }

    //Returns: creatore
    public String getCreatore(){
        return this.creatore;
    }

    public String getTesto(){
        return this.testo;
    }

    //Returns: this.numlike
    public int getN_likes(){
        return n_likes;
    }


    //TODO: vedere se passare ad un return String
    public void display (){
        System.out.println("\nCategoria: " + getCategory());
        System.out.println("Autore: " + getCreatore());
        System.out.println("Contenuto: " + getTesto());
        System.out.println("Like totali: " + getN_likes());
        System.out.print("\n");
    }

    /*public String display2(){
        StringBuilder sb = new StringBuilder();
        sb.append(getCategory());
        sb.append(getCreatore());
        sb.append(getTesto());
        sb.append(getN_likes());
        return sb.toString();
    }*/

    public boolean isEquals(Data d){
        if(this.creatore.equals(d.getCreatore()) && this.categoria.equals(d.getCategory()) && this.testo.equals(d.getTesto()) && this.amici.equals(d.getAmici())){
            return true;
        }
        return false;
    }

    public void addLikes(String person){
        n_likes ++;
        amici.add(person);

    }

    public boolean isFriend(String person){
        return amici.contains(person);
    }

        //utilizzato per ordinare i dati dal punto di vista del numero di like nei confronti o dal punto di vista lessicografico
    @Override
    public int compareTo(Data object) {
        if(this.equals(object)) return 0;
        if(this.n_likes == object.getN_likes()){//alllra ordino per nome di categoria
            return this.categoria.compareTo(object.getCategory());
        }
        else return object.getN_likes() - this.n_likes;
    }

    //_________________ Metodi utilizzati per la seconda implementazione _________________

    public ArrayList<String> getLiked() { //Return di una lista che contiene gli amici che hanno messo like al dato
        /* Requires: this.liked!=null
         * Returns: listof(forall i in liked)
         */
        if (this.liked==null) throw new NullPointerException();
        ArrayList<String> likes=new ArrayList<String>();
        for (String temp : liked) likes.add(temp);
        return likes;
    }

    //Decrementa numlike di uno
    public void minusLike() {
        //Modifies: n_like
        //Effects: this.n_like=this.n_like-1
        this.n_likes--;
    }

    //Rimuove dalla lista liked l'amico friend
    public void removelike(String friend) throws NullPointerException{
        /* Requires: this.friend!=null && liked!=null
         * Throws: NullPointerException se friend==null
         * Modifies: this.liked
         * Effects: forall i in liked ==> i!=friend
         */
        if (friend==null || this.liked==null) throw new NullPointerException();
        liked.remove(friend);
    }

    public void setAuthor(String author) {
        /* Requires: author!=null
         * Throws: NullPointerException se author==null
         * Modifies: this.author
         * Effects: this.author=author
         */
        if (author==null) throw new NullPointerException();
        this.creatore=author;
    }

    //Metodo usato per la put
    //Setta la categoria del dato per poi poterla stampare/ricercare
    public void setCategory(String category) {
        /* Requires: category!=null
           Modifies: this.category
           Effects: this.category=category */
        this.categoria=category;
    }

    //Usato per resettare il dato nel caso fosse stato modificato nel main e non tramite i metodi della board																					  */
    public void resetD() {
        //Effects: Resetta le variabili d'istanza del dato
        this.categoria = null;
        this.creatore = null;
        if (this.liked.size()!=0) this.liked=new ArrayList<String>();
        this.n_likes = 0;
    }

    public boolean setLike(String friend) { //Aggiunge un like al dato
        /* Requires: friend!=null && this.liked!=null
         * Throws: NullPointerException se friend==null || this.liked==null
         * Modifies: this.liked && this.numlike
         * Effects: !(this.liked.contains(friend)) ==> this.liked.add(friend) && this.numlike++
         * Returns: true se il like è stato aggiunto e l'amico inserito alla lista liked, false altrimenti
         */
        if (friend==null || this.liked==null) throw new NullPointerException();
        if (!liked.contains(friend)) { //Se l'amico passato per parametro non e' contenuto nella lista liked aggiungo un like al dato
            this.n_likes = this.n_likes + 1;
            liked.add(friend);
            return true;
        }
        return false;
    }


}
