import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
    AF:{<f(categoria)=(c0,....,cn), dove n = categoria.size()}
 *  IR: this.categoria!=null && this.autore!=null && this.password!=null
 * 	  && forall 0<=i,j<this.size() categoria[i]!=categoria[j]
 *
 *
 */

public class Board_2<E extends Data> implements DataBoard<E>{

    private ArrayList<Category> categoria;
    private String password;
    private String autore;


    public Board_2(String author, String passw) throws IllegalArgumentException {
        //Controllo se sia possibile creare la board con i parametri passati
        if (author == null) throw new NullPointerException();
        if (passw == null) throw new  NullPointerException();
        if (author.equals("") || passw.equals("")) throw new IllegalArgumentException();
        //Creo il collegamento string->list sia per dati che per friends ed inizializzo le varibili locali autore e password
        categoria = new ArrayList<Category>();
        this.password = passw;
        this.autore = author;
    }

    public void createCategory(String category, String passw) throws IllegalAccessException, IllegalArgumentException {
       // Check.checkPassword(passw);
        if(!passw.equals(password)) throw new IllegalAccessException();
        if(!category.matches("[a-zA-Z]+")) throw new IllegalArgumentException();
        //Controllo che la categoria che voglio aggiungere non sia già esistente
        for (Category tmp : this.categoria) if (tmp.getName().equals(category)) throw new IllegalArgumentException();
        Category newc= new Category(category); //Crea la nuova categoria
        this.categoria.add(newc); //Aggiunge la nuova categoria all'ArrayList category
    }

    @Override
    public void removeCategory(String category, String passw) throws NullPointerException, IllegalAccessError{
        /* Requires: Category!=null && passw!=null && this.passw==passw && this.category!=null
         * 			 && !(forall i in this.category ==> i.getName()!=Category)
         * Throws: NullPointerException se passw==null || Category==null || this.category==null || !(esiste(i) in this.category ==> i.getName()==Category)
         * Throws: InvalidPasswordException se this.passw!=passw
         * Modifies: category
         * Effects: forall i in category t.c i.getName()==Category ==> category.remove(i)
         */
        Check.checkPassword(passw);
        if (!this.password.equals(passw)) throw new IllegalAccessError();
        Category c = null;
        for (Category tmp : this.categoria) if (tmp.getName().equals(category)) c=tmp; //Prendo la categoria che ha il nome uguale alla categoria passata per parametro
        if (c==null) throw new NullPointerException(); //Se non trovo nessuna categoria che ha il nome uguale a Category
        else this.categoria.remove(c); //Rimuovo la categoria

    }

    @Override
    public void addFriend(String category, String passw, String friend) throws IllegalAccessError, NullPointerException, IllegalArgumentException, IllegalAccessException {
        Check.checkName(friend);
        if(!passw.equals(password)) throw new IllegalAccessError();
        Category cat = null;
        //Prendo la categoria che ha il nome uguale alla categoria passata per parametro
        for (Category temp : this.categoria) {
            if (temp.getName().equals(category)) cat = temp;
        }
        //Se nessuna categoria che ha il nome uguale a Category
        if (cat == null) throw new NullPointerException();
        else cat.addFriend(friend); //Aggiungo l'amico alla categoria

    }

    @Override
    public void removeFriend(String category, String passw, String friend) throws IllegalAccessError, NullPointerException {
        if(!passw.equals(password)) throw new IllegalAccessError();
        Category cat = null;
        //Cerco la categoria che ha il nome uguale alla categoria passata per parametro
        for (Category temp : this.categoria) {
            if (temp.getName().equals(category)) cat = temp;
        }
        //Non ho trovato nessuna categoria che ha il nome uguale a Category
        if (cat == null) throw new NullPointerException();
        //Rimuovo l'amico alla categoria
        cat.removeFriend(friend);

    }

    public boolean put(String passw, E dato, String category) throws IllegalAccessException,NullPointerException {
        if (category == null || passw == null || dato == null || this.categoria == null) throw new NullPointerException();
        if (!this.password.equals(passw)) throw new IllegalAccessException();
        Category cat = null;
        //Prendo la categoria che ha il nome uguale alla categoria passata per parametro
        for (Category temp : this.categoria) {
            if (temp.getName().equals(category)) cat = temp;
        }
        //Non ho trovato nessuna categoria che ha il nome uguale a Category
        if (cat == null) throw new NullPointerException();
        /*if (dato.getN_likes()!=0 || dato.getCreatore()!=null || dato.getCategory()!=null){
            dato.resetD();
        }*/
        //Faccio una copia deep del dato e lo aggiungo alla lista dei dati della categoria
        E datocloned = (E)dato.copyData();
        datocloned.setCategory(cat.getName());
        boolean test = cat.addDato(datocloned,this.getAuthor());
        //Returns true se è il dato è stato aggiunto, false altrimenti
        return test;
    }

    @Override
    public E get(String passw, E dato) throws IllegalAccessException {
        /*	Requires: passw!=null && dato!=null && this.category!=null && this.passw==passw
         *  Throws: NullPointerException se passw==null || dato==null || this.category==null
         *  Throws: InvalidPasswordException se this.passw!=passw
         *  Throws: DataNotFoundException se (forall i in category ==> (forall j in i.getDati() ==> j!=dato))
         *  Returns: firstof(forall i in category t.c i.getDati.contains(dato) ==> forall j in i.getDati() t.c j==dato))
         */
        if (passw==null || dato==null || this.categoria==null) throw new NullPointerException();
        if (!this.password.equals(passw)) throw new IllegalAccessException();
        E d = null;
        for (Category temp : this.categoria) {
            if (d == null) {
                //Controllo se esiste una categoria che contiene il dato e prendo il primo dato trovato
                if (temp.containsDato(dato)) {
                    d = (E) temp.getDato(dato);
                }
            }
        }
        //Se non ho trovato nessun dato nella board uguale al dato passato per parametro
        if (d == null) throw new NullPointerException();
        return d;

    }


    @Override
    public E remove(String passw, E dato) throws IllegalAccessException {
        /*	Requires: passw!=null && dato!=null && this.category!=null && this.passw==passw
         *  Throws: NullPointerException se passw==null || dato==null || this.category==null
         *  Throws: InvalidPasswordException se this.passw!=passw
         *  Throws: DataNotFoundException se (forall i in category ==> (forall j in i.getDati() ==> j!=dato))
         *  Modifies: this.category
         *  Effects: this.category.getDati().remove(firstof(forall i in category t.c i.getDati.contains(dato) ==> forall j in i.getDati() t.c j==dato)))
         *  Returns: firstof(forall i in category t.c i.getDati.contains(dato) ==> forall j in i.getDati() t.c j==dato))
         */
        if (passw == null || dato == null || this.categoria == null) throw new NullPointerException();
        if (!this.password.equals(passw)) throw new IllegalAccessException();
        E d = null;
        //Controllo se esiste una categoria che contiene un dato uguale al dato passato per parametro e salvo ed elimino il primo dato trovato che corrisponde
        for (Category temp : this.categoria)
        {
            if (d==null) { d=(E) temp.removeDato(dato);}
        }
        //Se non ho trovato nessun dato nella board uguale al dato passato per parametro
        if (d==null) throw new NullPointerException();
        return d;
    }

    @Override
    public List<E> getDataCategory(String passw, String category) throws IllegalAccessException {
        /* Requires: passw!=null && this.passw==passw && this.category!=null && Category!=null && !(forall i in category ==> i.getName!=Category)
         * Throws: NullPointerException passw==null || this.category==null || Category==null || forall i in category ==> i.getName!=Category
         * Throws: InvalidPasswordException se this.passw!=passw
         * Returns: list t.c list.contains(forall i in category t.c i.getName()==Category ==> forall j in i.getDati())
         */
        if (passw == null || category == null || this.categoria == null) throw new NullPointerException();
        if (!this.password.equals(passw)) throw new IllegalAccessException();
        Category c=null;
        List<E> newlist=new ArrayList<E>();
        for (Category tmp : this.categoria) if (tmp.getName().equals(category)) c=tmp; //Prendo la categoria che ha il nome uguale alla categoria passata per parametro
        if (c==null) throw new NullPointerException(); //Se non trovo nessuna categoria che ha il nome uguale a Category
        return c.getDati(); //Return della lista che contiene tutti i dati che appartengono alla categoria passata per parametro
    }

    @Override
    public Iterator<E> getIterator(String passw) throws IllegalAccessException {
        /* Requires: passw!=null && this.passw==passw && this.category!=null
         * Throws: NullPointerExcpetion se passw==null || this.category==null
         * Throws: InvalidPasswordException se this.passw!=passw
         * Returns: Iterator t.c iterates in (forall i in category ==> forall j in i.getDati())
         *
         */
        if (passw==null || this.categoria==null) throw new NullPointerException();
        if (!this.password.equals(passw)) throw new IllegalAccessException();
        //Metto nella lista "data" tutti i dati della board
        List<E> data = new ArrayList<E>();
        for (Category temp : this.categoria)
        {
            data.addAll(temp.getDati());
        }

        Collections.sort(data); //Sort usando il comparable sul numlike
        data=Collections.unmodifiableList(data); //Rendo la lista non modificabile
        return data.iterator(); //Faccio un return di un iterator che itera sulla lista creata
    }


    @Override
    public void insertLike(String friend, E dato) throws IllegalArgumentException,NullPointerException {
        if(friend == null || dato == null) throw new NullPointerException();
        if(friend.equals("")) throw new IllegalArgumentException();
        Category cat = null;
        for (Category temp: categoria) {
            if (temp.getName().equals(dato.getCategory())) {
                cat = temp;
            }
        }
        if(cat == null) throw  new IllegalArgumentException("Categoria inesistente");
        if(cat.containsDato(dato)){
            E datoTrovato = (E) cat.getDato(dato);
            if(cat.getFriends().contains(friend)){
                if(!datoTrovato.getLiked().contains(friend)){
                    datoTrovato.getLiked().add(friend);
                    datoTrovato.setLike(friend);
                    datoTrovato.display();
                } else throw  new IllegalArgumentException("Amico esistente");
            } else throw new IllegalArgumentException("Amico non trovato");
        } else throw new IllegalArgumentException("Dato non trovato" + dato.getTesto());
    }


    @Override
    public Iterator<E> getFriendIterator(String friend) throws NullPointerException{
        if (friend==null || this.categoria==null) throw new NullPointerException();

        List<E> data = new ArrayList<E>();

        //Per ogni categoria controlla se l'amico puo' vedere o no i dati, e mette nella lista data solo i dati che
        //Possono essere visti da esso.
        for (Category temp : this.categoria)
        {
            if (temp.getFriends().contains(friend)) data.addAll(temp.getDati());
        }

        data=Collections.unmodifiableList(data);
        return data.iterator(); //Return di un iteratore che itera sulla lista creata
    }


    public String getAuthor() {
        //Returns: this.author
        return this.autore; //Return dell'autore della board
    }





}