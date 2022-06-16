public interface StateChangeable<T extends Status> {
    abstract void changeState(Status newStatus);
}