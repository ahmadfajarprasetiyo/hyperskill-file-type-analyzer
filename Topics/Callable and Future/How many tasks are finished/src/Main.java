import java.util.*;
import java.util.concurrent.*;


class FutureUtils {

    public static int howManyIsDone(List<Future> items) {
        // write your code here
        int res = 0;
        for (Future item : items) {
            if (item.isDone()) {
                res++;
            }
        }

        return res;
    }

}