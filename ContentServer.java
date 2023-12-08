import contentserver.PutContentThread;
import shares.Constant;
import shares.LamportClock;

public class ContentServer
{
    /**
     * implement clock, run thread
     * @param args
     */
    public static void main( String[] args ) {
        if(args.length == 1) Constant.DIR_FOLDER_CONTENT = args[0];

        LamportClock clock = new LamportClock();
        PutContentThread putContentThread = new PutContentThread(clock);
        Thread thread = new Thread(putContentThread);
        thread.start();
    }


}
