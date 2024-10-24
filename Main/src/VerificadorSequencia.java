import java.util.NoSuchElementException;
import java.util.Scanner;

class Celula<E> {
    private E item;
    private Celula<E> proximo;

    public Celula() {
        this(null, null);
    }

    public Celula(E item, Celula<E> proximo) {
        this.item = item;
        this.proximo = proximo;
    }

    public E getItem() {
        return item;
    }

    public Celula<E> getProximo() {
        return proximo;
    }
}

class Pilha<E> {
    private Celula<E> topo;
    private Celula<E> fundo;

    public Pilha() {
        Celula<E> sentinela = new Celula<E>();
        fundo = sentinela;
        topo = sentinela;
    }

    public boolean vazia() {
        return fundo == topo;
    }

    public void empilhar(E item) {
        topo = new Celula<E>(item, topo);
    }

    public E desempilhar() {
        E desempilhado = consultarTopo();
        topo = topo.getProximo();
        return desempilhado;
    }

    public E consultarTopo() {
        if (vazia()) {
            throw new NoSuchElementException("Nao há nenhum item na pilha!");
        }
        return topo.getItem();
    }
}

public class VerificadorSequencia {

    public static boolean verificaBemFormada(String expressao) {
        Pilha<Character> pilha = new Pilha<>();

        for (char c : expressao.toCharArray()) {
            if (c == '(' || c == '[') {
                pilha.empilhar(c);
            } else if (c == ')' || c == ']') {
                if (pilha.vazia()) {
                    return false;
                }
                char topo = pilha.consultarTopo();
                if ((c == ')' && topo != '(') || (c == ']' && topo != '[')) {
                    return false;
                }
                pilha.desempilhar();
            }
        }
        return pilha.vazia();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String entrada;

        while (true) {
            entrada = scanner.nextLine();

            if (entrada.equals("FIM")) {
                break;
            }

            if (verificaBemFormada(entrada)) {
                System.out.println("correto");
            } else {
                System.out.println("incorreto");
            }
        }
        scanner.close();
    }
}