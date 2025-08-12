package in.ratansgh.service.implementation;

import in.ratansgh.exception.FullParkingLot;

import java.util.LinkedList;
import java.util.Queue;

public class SlotManager {
    private final Queue<Integer> slots;

    public SlotManager(int capacity) {
        slots = new LinkedList<>();
        for (int i = 1; i <= capacity; i++) {
            slots.offer(i);
        }
    }

    public int assignSlot() {
        if (slots.isEmpty()) {
            throw new FullParkingLot("Parking lot is full.");
        }
        return slots.poll();
    }

    public void releaseSlot(int slot) {
        slots.offer(slot);
    }
}
