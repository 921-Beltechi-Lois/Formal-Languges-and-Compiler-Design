

public class SymbolTable {
    private int size;
    private HashTable<String> identifiersHashTable;
    private HashTable<Integer> intConstantsHashTable;
    private HashTable<String> stringConstantsHashTable;

    public SymbolTable(int size) {
        this.size = size;
        this.identifiersHashTable = new HashTable<>(size);
        this.intConstantsHashTable = new HashTable<>(size);
        this.stringConstantsHashTable = new HashTable<>(size);
    }


    @Override
    public String toString() {
        return "SymbolTable{" +
                "identifiersHashTable=" + identifiersHashTable +
                "\nintConstantsHashTable=" + intConstantsHashTable +
                "\nstringConstantsHashTable=" + stringConstantsHashTable +
                '}';
    }
}