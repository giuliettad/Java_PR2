public class Check {
    /**
     * la classe check contiene i metodi di verifica di corretto inserimento dei dati
     */

    public static void checkName(String user) throws NullPointerException, IllegalArgumentException{
            if(user == null) throw new NullPointerException();
            if(user.length() == 0 || user.length() > 64) throw new IllegalArgumentException();
    }

    public static void chechCategory(String category) throws NullPointerException, IllegalArgumentException{
        if(category == null) throw new NullPointerException();
        if (category.length() == 0 || !category.matches("[a-zA-Z0-9]+") ) throw new IllegalArgumentException();
    }


    public static void checkText(String text) throws NullPointerException, IllegalArgumentException {
        if (text == null) throw new NullPointerException();
        if(text.length() == 0 || text.length() > 256) throw new IllegalArgumentException();
    }

    public static void checkPassword(String passw) throws NullPointerException, IllegalArgumentException{
        if(passw == null) throw new NullPointerException();
        if(passw.length() == 0 || passw.length() < 8 || passw.matches("[a-zA-Z0-9]+")) throw new IllegalArgumentException(); //Regex: deve contenere caratteri alfanumerici
    }


}
