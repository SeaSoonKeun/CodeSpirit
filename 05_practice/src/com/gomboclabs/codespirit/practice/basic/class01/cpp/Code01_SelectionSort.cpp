#include <iostream>
#include <algorithm>
#include <random>
#include <vector>
#include <chrono>

using namespace std;

void selectionSort(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) {
        return;
    }

    for (int i = 0; i < arr.size(); i++) {
        int minIndex = i;
        for (int j = i + 1; j < arr.size(); j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        swap(arr[i], arr[minIndex]);
    }
}

// for test
void comparator(vector<int>& arr) {
    sort(arr.begin(), arr.end());
}

// for test
vector<int> generateRandomArray(int maxSize, int maxValue) {
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dis(-maxValue, maxValue);

    int size = dis(gen);
    vector<int> arr(size);
    for (int i = 0; i < size; i++) {
        arr[i] = dis(gen);
    }
    return arr;
}

// for test
vector<int> copyArray(const vector<int>& arr) {
    return arr;
}

// for test
bool isEqual(const vector<int>& arr1, const vector<int>& arr2) {
    return arr1 == arr2;
}

// for test
void printArray(const vector<int>& arr) {
    for (int i = 0; i < arr.size(); i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

// for test
int main() {
    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;
    bool succeed = true;
    // 获取开始时间点
    auto start = std::chrono::high_resolution_clock::now();
    for (int i = 0; i < testTime; i++) {
        vector<int> arr1 = generateRandomArray(maxSize, maxValue);
        vector<int> arr2 = copyArray(arr1);

        selectionSort(arr1);
        comparator(arr2);

        if (!isEqual(arr1, arr2)) {
            succeed = false;
            printArray(arr1);
            printArray(arr2);
            break;
        }
    }

    cout << (succeed ? "Nice!" : "Fucking fucked!") << endl;
    // 获取结束时间点
    auto end = std::chrono::high_resolution_clock::now();

    // 计算执行时间
    std::chrono::duration<double> duration = end - start;
    std::cout << "Execution time: " << duration.count() << " seconds\n" << std::endl;

    return 0;
}