public class SweetsBasket extends Gift {
    private boolean includeNuts;

    public SweetsBasket(String id, char size, Boolean includeNuts) {
        super(id, size);

        this.includeNuts = includeNuts;
    }

    public boolean includeNuts() {
        return this.includeNuts;
    }

    public void setIncludeNuts(Boolean includeNuts) {
        this.includeNuts = includeNuts;
    }
    
    @Override
    protected void display() {
        System.out.printf("\nSweetsBasket [haveNuts=%s size=%s id=%s price=%s]",
                this.includeNuts(), this.getSize(), this.getId(), String.format("%,.2f", this.getPrice()));
    }
}
