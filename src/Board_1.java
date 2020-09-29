import java.util.*;
/*
 * AF: {f(category_0)=({dato_0,...,dato_N},{friend_0,...,friend_N}),password,autore} ... {f(category_N)=({dato_0,...,dato_N},{friend_0,...,friend_N}),password,autore}
 * IR: hash_categorie!=null && hash_amici!=null && autore!=null && password!=null
 * 	  && forall 0<=i,j<size() hash_categorie(i)!=hash_categorie(j) && forall 0<=i,j<size() hash_amici(i)!=hash_amici(j)
 *    && this.hash_categorie.size()==this.hash_amici.size()
 */

public class Board_1<E extends Data> implements DataBoard<E> {
    private String autore;      //autore della bacheca
    private String password;    // password dell'autore della bacheca
    private HashMap<String, ArrayList<E>> hash_categorie;  //Struttura board1 è caratterizzata dalla categoria e tuttii dati associati a quella categoria
    private HashMap<String, ArrayList<String>> hash_amici;       //Struttura board1 è caratterizzata dalla categoria e la lista di amici associati

    //restituisce l'arrayList degli amici associato alla categoria
    public ArrayList<String> getHash(String cat){
        return this.hash_amici.get(cat);
    }

    //Stampa tutte le info del dato se presente nella categoria
    public void displayDato(String cat, E dat){
        ArrayList<E> temp = hash_categorie.get(cat);
        for(E dd : temp){
            if(dat.isEquals(dd)){
                dat.display();
            }
        }
    }
//Restituisce il dato associato a una categoria
    public E getDatoFromHash(String cat, E dato){
        ArrayList<E> temp = hash_categorie.get(cat);
        for(E dd : temp){
            if(dato.isEquals(dd)){
                return dd;
            }
        }
        return null;
    }

    public Board_1(String autore, String password) throws NullPointerException, IllegalArgumentException{
        Check.checkName(autore);
        this.autore = autore;
        Check.checkPassword(password);
        this.password = password;
        hash_categorie = new HashMap<String, ArrayList<E>>();
        hash_amici = new HashMap<String, ArrayList<String>>();
    }

    @Override
    public void createCategory(String category, String passw) throws IllegalAccessError {
        Check.chechCategory(category);
        if(!passw.equals(password)) throw new IllegalAccessError();
        if(this.hash_categorie.containsKey(category)) throw new IllegalArgumentException();
        hash_categorie.put(category, new ArrayList<E>());
        // System.out.println(Arrays.asList(hash_categorie));
        hash_amici.put(category, new ArrayList<String>());
    }

    @Override
    public void removeCategory(String category, String passw) throws IllegalAccessError, IllegalArgumentException {
        if(!passw.equals(password)) throw new IllegalAccessError();
        if(!this.hash_categorie.containsKey(category)) throw new IllegalArgumentException();
        hash_categorie.remove(category);
        hash_amici.remove(category);
    }

    @Override
    public void addFriend(String category, String passw, String friend) throws IllegalAccessError, IllegalArgumentException {
        Check.checkName(friend);
        if(!passw.equals(password)) throw new IllegalAccessError();
        if(!this.hash_amici.containsKey(category)) throw new IllegalArgumentException();
        ArrayList<String> lista = hash_amici.get(category);
        if(lista.contains(friend)) throw new IllegalArgumentException();
        lista.add(friend);

        for(Map.Entry<String, ArrayList<String>> entry : hash_amici.entrySet()){
            String key = entry.getKey();
            ArrayList<String> values = entry.getValue();
        }
    }

    @Override
    public void removeFriend(String category, String passw, String friend) throws IllegalAccessError, IllegalArgumentException {
        if(!passw.equals(password)) throw new IllegalAccessError();
        if(!this.hash_amici.containsKey(category)) throw new IllegalArgumentException();
        ArrayList<String> lista = hash_amici.get(category);
        if(!lista.contains(friend)) throw new IllegalArgumentException();
        lista.remove(friend);
    }

   @Override
    public boolean put(String passw, E dato, String category){
        if(!passw.equals(password)|| !this.hash_categorie.containsKey(category))  return false;
        ArrayList<E> lista = hash_categorie.get(category);
        if (lista.contains(dato)) return false;
        lista.add(dato);
       for(Map.Entry<String, ArrayList<E>> entry : hash_categorie.entrySet()){
           String key = entry.getKey();
           ArrayList<E> values = entry.getValue();
          /* for (Data data : values){
               System.out.println("Chiave: " + key + ". Testo: " + data.getTesto());
           }*/
       }
       return true;
    }

    @Override
    public E get(String passw, E dato) throws IllegalAccessError, IllegalArgumentException{
        if(!passw.equals(password)) throw new IllegalAccessError();
        //values restituisce una collezione di arraylist
        E copy = null;
        if(dato == null) throw new IllegalArgumentException();

        for(ArrayList<E> values : this.hash_categorie.values()){
            for(Data data : values){
                if(data.isEquals(dato)){ //Uso metodo creato nella classe Data
                    copy = dato;
                    copy.display();
                }
            }
        }
        return copy;
    }

    @Override
    public E remove(String passw, E dato) throws NullPointerException, IllegalAccessError{
        if(dato == null) throw new NullPointerException();
        if(!passw.equals(password)) throw new IllegalAccessError();
        E d = null;
        boolean trovato = false;
        for(ArrayList<E> j : this.hash_categorie.values()) {
            for (Data data : j) {
                if(data.isEquals(dato)) {
                    j.remove(dato);
                    d = dato;
                    trovato = true;
                }
                else{
                    trovato = false;
                }
            }
        }
        if(trovato){
            System.out.println("\nDato rimosso: ");
            d.display();
        }
        else{
            System.out.println("Errore! Impossibile rimuovere il dato perchè non presente.");
        }
        return d;
    }

    @Override
    public List<E> getDataCategory(String passw, String category) throws IllegalAccessError, IllegalArgumentException {
        if(!passw.equals(password)) throw new IllegalAccessError();
        if(!this.hash_categorie.containsKey(category)) throw new IllegalArgumentException();
        ArrayList<E> li = hash_categorie.get(category);
        for (Data element : li) {
            element.display();
        }
        return li; //Restituisco una nuova lista
    }



    @Override
    public void insertLike(String friend, E dato) throws NullPointerException, IllegalArgumentException, IllegalAccessException{

        if(friend == null || dato == null){
            throw new NullPointerException();
        }
        if(friend.equals("")){
            throw new IllegalArgumentException();
        }
        if (!hash_amici.get(dato.getCategory()).contains(friend)) {
            throw new IllegalAccessException();
        }
        for(String i: this.hash_categorie.keySet()){      //keyset restituisce un insieme di tutte le chiavi, cioè le categorie
            // hash_categorie.get(i);  restituisce la lista, devo cercare il dato
            if(hash_categorie.get(i).contains(dato)){

                if(hash_amici.get(i).contains(friend)){
                    for(E dat : hash_categorie.get(i)){
                        if(dat.isEquals(dato)){
                            dat.addLikes(friend);
                        }
                    }
                }
            }
        }

    }


    @Override
    public Iterator<E> getIterator(String passw) throws IllegalAccessError {
        if(!passw.equals(password)) throw new IllegalAccessError();
        TreeSet<E> ordered = new TreeSet<E>(); //cercare
        for(ArrayList<E> i: this.hash_categorie.values()){
            for(E j: i){
                ordered.add(j);
            }
        }
        return Collections.unmodifiableSortedSet((SortedSet<E>) ordered).iterator();
    }



    @Override
    public Iterator<E> getFriendIterator(String friend) throws IllegalArgumentException,NullPointerException {
        if (friend == null) throw new NullPointerException();
        if(friend.equals("")) throw new IllegalArgumentException();
        Set<E> set = new HashSet<E>();
        for(ArrayList<E> i: this.hash_categorie.values()){
            for(E j: i){
                if(j.isFriend(friend))
                    set.add(j);
            }
        }
        return Collections.unmodifiableSet( (Set<E>) set).iterator();
    }
}
