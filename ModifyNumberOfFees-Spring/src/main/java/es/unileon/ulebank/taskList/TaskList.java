/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.taskList;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.time.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author runix
 */
public class TaskList {

    /**
     * Tasks to execute.
     */
    private final List<Task> tasks;

    /**
     * Tasks executed.
     */
    private final List<Task> tasksDone;

    /**
     * Deleted tasks before execute it.
     */
    private final List<Task> deletedTasks;

    /**
     * Tasks's comparator to sort by effective date.
     */
    private final TaskDateComparator comparator;

    /**
     * Bank's time.
     */
    private final Time time;

    /**
     * Create a new tasklist.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.tasksDone = new ArrayList<>();
        this.deletedTasks = new ArrayList<>();
        this.comparator = new TaskDateComparator();
        this.time = Time.getInstance();
    }

    /**
     *
     * @param task
     * @return
     */
    public synchronized boolean addTask(Task task) {
        boolean add = true;
        if (task != null) {
            for (int i = 0; i < tasks.size() && add; i++) {
                if (task.getID().compareTo(this.tasks.get(i).getID()) == 0) {
                    add = false;
                }
            }
            this.tasks.add(task);
            this.sort();
        }
        return add;
    }

    /**
     *
     * @param task
     * @return
     */
    public boolean deleteTask(Task task) {
        boolean delete = false;
        if (task != null) {
            for (int i = 0; i < tasks.size() && !delete; i++) {
                if (task.getID().compareTo(this.tasks.get(i).getID()) == 0) {
                    Task c = this.tasks.get(i);
                    this.tasks.remove(i);
                    this.deletedTasks.add(c);
                    delete = true;
                }
            }
            this.sort();
        }
        return delete;
    }

    /**
     *
     * @param id
     */
    public void undoTask(Handler id) {
        for (int i = 0; i < this.tasksDone.size(); i++) {
            Task c = this.tasksDone.get(i);
            if (c.getID().compareTo(id) == 0) {
                c.undo();
            }
        }
    }

    /**
     *
     */
    public void executeTasks() {
        int i = 0;
        while (this.tasks.get(i).getEffectiveDate().getTime() <= this.time.getTime()) {
            Task c = this.tasks.get(i);
            c.execute();
            this.tasks.remove(i);
            this.tasksDone.add(c);
            ++i;
        }
        this.sort();;
    }

    private void sort() {
        Collections.sort(this.tasks, this.comparator);
        Collections.sort(this.tasksDone, this.comparator);
    }

    /**
     *
     * @return
     */
    public List<Task> getDeteledTasks() {
        return new ArrayList<>(this.deletedTasks);
    }

    /**
     *
     * @return
     */
    public List<Task> getTaskList() {
        return new ArrayList<>(this.tasks);
    }

    /**
     *
     * @return
     */
    public List<Task> getTasksListDone() {
        return new ArrayList<>(this.tasksDone);
    }

}
