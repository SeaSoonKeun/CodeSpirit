package main

import (
	"fmt"
	"math/rand"
	"sort"
	"time"
)

func selectionSort(arr []int) {
	size := len(arr)
	if size < 2 {
		return
	}

	for i := 0; i < size; i++ {
		minIndex := i
		for j := i + 1; j < size; j++ {
			if arr[j] < arr[minIndex] {
				minIndex = j
			}
		}
		arr[i], arr[minIndex] = arr[minIndex], arr[i]
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

		selectionSort(arr1)
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
