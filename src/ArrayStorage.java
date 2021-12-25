import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int resumeCount;

    void clear() {
        Arrays.fill(storage, 0, resumeCount, null);
        resumeCount = 0;
    }

    void save(Resume r) {
        storage[resumeCount] = r;
        resumeCount++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i] != null) {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].toString().equals(uuid)) {
                resumeCount--;
                System.arraycopy(storage, i + 1, storage, i, resumeCount - i);
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount);
    }

    int size() {
        return resumeCount;
    }
}
