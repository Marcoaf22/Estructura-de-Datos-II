package Preg2;

public class Lista {    //Usted debe declarar los campos de esta class

    Nodo personaje;
    Nodo vida;

    public Lista() {     //Usted debe implementar éste método.
        personaje = null;
        vida = null;
    }

    public void add(int nroPersonaje, int cantVidas) {   //Usted debe implementar éste método.
        addR(personaje, vida, nroPersonaje, cantVidas);
    }

    //Procedimiento ya que devuelve solo un Nodo y no 2
    private void addR(Nodo p, Nodo v, int nroPersonaje, int cantVidas) {
        if (p == null) {
            personaje = new Nodo(nroPersonaje);
            vida = new Nodo(cantVidas);
        } else {
            if (p.getData() == nroPersonaje) {
                v.setData(cantVidas);
                return;
            }
            if (p.getLink() == null) {
                p.setLink(new Nodo(nroPersonaje));
                v.setLink(new Nodo(cantVidas));
            } else {
                addR(p.getLink(), v.getLink(), nroPersonaje, cantVidas);
            }
        }
    }
    
    public void shoot(int nroPersonaje) {    //Usted debe implementar éste método.
        shootR(personaje, vida, nroPersonaje);
    }

    public void shootR(Nodo p, Nodo v, int nroPersonaje) {
        if (p != null) {
            if (p.getData() == nroPersonaje) {
                v.Data--;
                if (v.getData() == 0) {
                    personaje = p.getLink();
                    vida = v.getLink();
                }
            } else if (p.getLink() != null && p.getLink().Data == nroPersonaje) {
                v.getLink().Data--;
                if (v.getLink().Data == 0) {
                    p.setLink(p.getLink().getLink());
                    v.setLink(v.getLink().getLink());

                }
            } else {
                shootR(p.getLink(), v.getLink(), nroPersonaje);
            }

        }
    }

    public void mostrar() {     //Usted debe implementar éste método.
        Nodo p = personaje;
        Nodo v = vida;
        while (p != null) {
            System.out.print("[" + p.Data + "/" + v.Data + "]->");
            p = p.getLink();
            v = v.getLink();
        }
        System.out.println("");
    }

}
