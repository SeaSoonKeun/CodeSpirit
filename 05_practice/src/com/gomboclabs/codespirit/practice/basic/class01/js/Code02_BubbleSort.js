// 练手
function bubbleSort(arr) {
    for (let i = 0; i < arr.length; i++) {
        let isSwapped = false;
        for (let j = arr.length - 1; j > i ; j--) {
            if (arr[j] < arr[j-1]) {
                isSwapped = true;
                swap(arr, j, j-1);
            }
        }
        if (!isSwapped) {
            break;
        }
    }
}

function swap(arr, i, j) {
    const temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

function comparator(arr) {
    arr.sort((a, b) => a - b);
}

function generateRandomArray(maxSize, maxValue) {
    const arr = [];
    for (let i = 0; i < Math.floor(Math.random() * maxSize); i++) {
        arr.push(Math.floor(Math.random() * (2 * maxValue + 1)) - maxValue);
    }
    return arr;
}

function copyArray(arr) {
    return arr.slice();
}

function isEqual(arr1, arr2) {
    if (arr1 === null && arr2 === null) {
        return true;
    }
    if (arr1 === null || arr2 === null || arr1.length !== arr2.length) {
        return false;
    }
    for (let i = 0; i < arr1.length; i++) {
        if (arr1[i] !== arr2[i]) {
            return false;
        }
    }
    return true;
}

function printArray(arr) {
    console.log(arr.join(' '));
}

function main() {
    console.time("executionTime");
    const testTime = 500000;
    const maxSize = 100;
    const maxValue = 100;
    let succeed = true;
    for (let i = 0; i < testTime; i++) {
        const arr1 = generateRandomArray(maxSize, maxValue);
        const arr2 = copyArray(arr1);
        bubbleSort(arr1);
        comparator(arr2);
        if (!isEqual(arr1, arr2)) {
            succeed = false;
            printArray(arr1);
            printArray(arr2);
            break;
        }
    }
    console.log(succeed ? 'Nice!' : 'Fucking fucked!');
    console.timeEnd("executionTime");
}

main();