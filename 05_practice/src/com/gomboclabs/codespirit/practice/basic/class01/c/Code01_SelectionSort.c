#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

void selectionSort(int arr[], int size) {
    if (size < 2) {
        return;
    }

    for (int i = 0; i < size; i++) {
        int minIndex = i;
        for (int j = i + 1; j < size; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        int temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }
}
// for test
int compare(const void* a, const void* b) {
    return (*(int*)a - *(int*)b);
}

// for test
void comparator(int arr[], int size) {
    qsort(arr, size, sizeof(int), compare);
}

// for test
void generateRandomArray(int arr[], int size, int maxValue) {
    for (int i = 0; i < size; i++) {
        arr[i] = rand() % (2 * maxValue + 1) - maxValue;
    }
}

// for test
void copyArray(const int src[], int dest[], int size) {
    for (int i = 0; i < size; i++) {
        dest[i] = src[i];
    }
}

// for test
bool isEqual(const int arr1[], const int arr2[], int size) {
    for (int i = 0; i < size; i++) {
        if (arr1[i] != arr2[i]) {
            return false;
        }
    }
    return true;
}

// for test
void printArray(const int arr[], int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

// for test
int main() {
    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;
    bool succeed = true;
    clock_t start, end;
    double cpu_time_used;
    // Start the timer
    start = clock();
    for (int i = 0; i < testTime; i++) {
        int size = rand() % maxSize + 1;
        int* arr1 = malloc(size * sizeof(int));
        int* arr2 = malloc(size * sizeof(int));

        generateRandomArray(arr1, size, maxValue);
        copyArray(arr1, arr2, size);

        selectionSort(arr1, size);
        comparator(arr2, size);

        if (!isEqual(arr1, arr2, size)) {
            succeed = false;
            printArray(arr1, size);
            printArray(arr2, size);
            break;
        }

        free(arr1);
        free(arr2);
    }

    printf("%s\n", succeed ? "Nice!" : "Fucking fucked!");

    // Stop the timer
    end = clock();

    // Calculate the CPU time used
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;

    // Print the execution time
    printf("Execution time: %f seconds\n", cpu_time_used);
    return 0;
}