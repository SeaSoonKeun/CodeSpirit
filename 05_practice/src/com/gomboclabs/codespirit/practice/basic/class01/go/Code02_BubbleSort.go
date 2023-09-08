package main

// 你可以将其包名设置为main。这样，它将成为一个独立的可执行文件，
//并且可以通过go build命令构建和运行。

import (
	"fmt"
	"math/rand"
	"sort"
	"time"
)

func bubbleSort(arr []int) {
	size := len(arr)
	if size < 2 {
		return
	}

	for i := 0; i < size; i++ {
		isSwapped := false
		for j := size - 1; j > i; j-- {
			if arr[j] < arr[j-1] {
				isSwapped = true
				arr[j], arr[j-1] = arr[j-1], arr[j]
			}
		}
		if isSwapped != true {
			break
		}
	}
}

func comparator(arr []int) {
	sort.Ints(arr)
}

func generateRandomArray(size, maxValue int) []int {
	arr := make([]int, size)
	rand.Seed(time.Now().UnixNano())
	for i := 0; i < size; i++ {
		arr[i] = rand.Intn(2*maxValue+1) - maxValue
	}
	return arr
}

func copyArray(src []int) []int {
	dest := make([]int, len(src))
	copy(dest, src)
	return dest
}

func isEqual(arr1, arr2 []int) bool {
	if len(arr1) != len(arr2) {
		return false
	}

	for i := 0; i < len(arr1); i++ {
		if arr1[i] != arr2[i] {
			return false
		}
	}
	return true
}

func printArray(arr []int) {
	for _, num := range arr {
		fmt.Printf("%d ", num)
	}
	fmt.Println()
}

func main() {
	testTime := 500000
	maxSize := 100
	maxValue := 100
	succeed := true

	// Start the timer
	start := time.Now()

	for i := 0; i < testTime; i++ {
		size := rand.Intn(maxSize) + 1
		arr1 := generateRandomArray(size, maxValue)
		arr2 := copyArray(arr1)

		bubbleSort(arr1)
		comparator(arr2)

		if !isEqual(arr1, arr2) {
			succeed = false
			printArray(arr1)
			printArray(arr2)
			break
		}
	}

	if succeed {
		fmt.Println("Nice!")
	} else {
		fmt.Println("Fucking fucked!")
	}

	// Stop the timer
	elapsed := time.Since(start)

	// Print the execution time
	fmt.Printf("Execution time: %s\n", elapsed)
}
