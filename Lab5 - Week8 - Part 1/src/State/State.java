package State;

import LR.Grammar;

import java.util.*;

/**
 * The state is composed of a set of items (items being of the form [A->alpha.beta]
 */
public class State {

    private StateActionType stateActionType;
    private Set<Item> items;


    public State(Set<Item> states){
        this.items = states;
    }

    public Set<Item> getItems(){
        return items;
    }

    @Override
    public String toString(){
        return items.toString();
    }

    public List<String> getSymbolsSucceedingTheDot(){
        Set<String> symbols = new LinkedHashSet<>();

        for(Item i: items){
            if(i.getPositionForDot() < i.getRightHandSide().size())
                symbols.add(i.getRightHandSide().get(i.getPositionForDot()));
        }

        return new ArrayList<>(symbols);
    }

    /**
     * With this method we set the action for the state.
     * The action can be: SHIFT, REDUCE, ACCEPT, REDUCE_REDUCE_CONFLICT or SHIFT_REDUCE_CONFLICT
     */
    public void setActionForState(){
        if(items.size() == 1 && ((Item)items.toArray()[0]).getRightHandSide().size() == ((Item)items.toArray()[0]).getPositionForDot() && ((Item)this.items.toArray()[0]).getLeftHandSide() == Grammar.enrichedStartingGrammarSymbol){
            this.stateActionType = StateActionType.ACCEPT;
        } else if(items.size() == 1 && ((Item) items.toArray()[0]).getRightHandSide().size() == ((Item) items.toArray()[0]).getPositionForDot())
        {
            this.stateActionType = StateActionType.REDUCE;
        } else if(items.size() >= 1 && this.items.stream().allMatch(i -> i.getRightHandSide().size() > i.getPositionForDot())){
            this.stateActionType = StateActionType.SHIFT;
        } else if(items.size() > 1 && this.items.stream().allMatch(i -> i.getRightHandSide().size() == i.getPositionForDot())){
            this.stateActionType = StateActionType.REDUCE_REDUCE_CONFLICT;
        } else {
            this.stateActionType = StateActionType.SHIFT_REDUCE_CONFLICT;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(items);
    }

    @Override
    public boolean equals(Object item){
        if(item instanceof  State){
            return ((State) item).getItems().equals(this.getItems());
        }

        return false;
    }

}