package gomboclabs.codespirit.practice.basic.class01.scala

import scala.util.Random

object Code02_BubbleSort {

  //  练手
  def bubbleSort(arr: Array[Int]): Unit = {
    var swapped = false;
    for (i <- 0 until arr.length  by 1) {
      for (j <- arr.length - 1 until i by -1) {
        if (arr(j) < arr(j - 1)){
          swapped = true
          swap(arr, j, j-1)
        }
      }
      // for (i <- 0 until arr.length - 1 by 1 if !isSorted) {} 可使用if替代
      if (!swapped) {
        return
      }
    }
  }

  def swap(arr: Array[Int], i: Int, j: Int): Unit = {
    val tmp = arr(i)
    arr(i) = arr(j)
    arr(j) = tmp
  }

  // for test
  def comparator(arr: Array[Int]): Unit = {
    arr.sorted
  }

  // for test
  def generateRandomArray(maxSize: Int, maxValue: Int): Array[Int] = {
    val random = new Random()
    val size = random.nextInt(maxSize + 1)
    Array.fill(size)(random.nextInt(2 * maxValue + 1) - maxValue)
  }

  // for test
  def copyArray(arr: Array[Int]): Array[Int] = {
    if (arr == null) {
      null
    } else {
      arr.clone()
    }
  }

  // for test
  def isEqual(arr1: Array[Int], arr2: Array[Int]): Boolean = {
    if (arr1 == null && arr2 == null) {
      true
    } else if (arr1 == null || arr2 == null) {
      false
    } else {
      arr1.sameElements(arr2)
    }
  }

  // for test
  def printArray(arr: Array[Int]): Unit = {
    if (arr != null) {
      println(arr.mkString(" "))
    }
  }

  // for test
  def main(args: Array[String]): Unit = {
    val startTime = System.nanoTime()
    val testTime = 500000
    val maxSize = 100
    val maxValue = 100
    val succeed = true

    for (_ <- 0 until testTime) {
      val arr1 = generateRandomArray(maxSize, maxValue)
      val arr2 = copyArray(arr1)

      bubbleSort(arr1)
      comparator(arr2)
    }

    println(if (succeed) "Nice!" else "Fucking fucked!")
    val endTime = System.nanoTime()
    val executionTime = (endTime - startTime) / 1000000

    println(s"执行时间：$executionTime 毫秒")
  }
}