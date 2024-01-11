public class Queue<Integer> {

    private Node<Integer> first;
    private Node <Integer> last;
    private int size;

    public Queue()
    {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void insert (int row, int col)
    {
        Node<Integer> temp = new  Node <Integer> (row, col);
        if (this.first == null){
            this.first = temp;
            this.last = temp;
        }
        else if (this.first == this.last){
            this.last = temp;
            this.first.setNext(this.last);
        }
        else {
            this.last.setNext(temp);
            this.last = temp;
        }
        size++;
    }

    public int[] remove()
    {
        int row = this.first.getRow();
        int col = this.first.getCol();
        this.first = this.first.getNext();
        if(this.first == this.last)
            this.last = null;
        int[] arr = {row, col};
        size--;
        return arr;
    }

    public int[] head()
    {
        int[] arr = {first.getRow(), first.getCol()};
        return arr;
    }

    public int getSize() {
        return size;
    }

    public boolean isInQueue(int row, int col){
        int[] individualMove;
        for (int i = 0; i < size; i++) {
            individualMove = this.remove();
            if (individualMove[0] == row && individualMove[1] == col){
                return true;
            }
            this.insert(individualMove[0], individualMove[1]);
        }
        return false;
    }



}