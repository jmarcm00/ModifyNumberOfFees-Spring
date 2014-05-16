/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.taskList;

import java.util.Comparator;

/**
 * Sort Commands by date
 *
 * @author runix
 */
public class TaskDateComparator implements Comparator<Task> {

    /**
     * Compare commands for sorting
     *
     * @param o1
     * @param o2
     *
     * @return (o1 == o2 -> 0, o1 > o2 , 1, o2 > o1 -1 )
     */
    @Override
    public int compare(Task o1, Task o2) {
        if (o1.getEffectiveDate().getTime() == o2.getEffectiveDate().getTime()) {
            return 0;
        } else if (o1.getEffectiveDate().getTime() > o2.getEffectiveDate().getTime()) {
            return 1;
        }
        return -1;
    }

}
