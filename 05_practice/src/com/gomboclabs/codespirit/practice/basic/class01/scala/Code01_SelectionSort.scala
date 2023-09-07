import scala.util.Random

object Code01_SelectionSort {
  //  def selectionSort(arr: Array[Int]): Unit = {
  //    if (arr == null || arr.length < 2) {
  //      return
  //    }
  //
  //    for (i <- 0 until arr.length) {
  //      var minIndex = i
  //      for (j <- i + 1 until arr.length) {
  //        if (arr(j) < arr(minIndex)) {
  //          minIndex = j
  //        }
  //      }
  //      swap(arr, i, minIndex)
  //    }
  //  }

  //  练手
  def selectionSort(arr: Array[Int]): Unit = {
    if (arr == null || arr.length < 2) {
      return
    }

    for (i <- arr.indices) {
      var minIndex = i
      for (j <- i + 1 until arr.length) {
        minIndex = if (arr(j) < arr(minIndex)) j else minIndex
      }
      if (minIndex == i) {
        swap(arr, i, minIndex)
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
    val testTime = 50
    val maxSize = 100
    val maxValue = 100
    val succeed = true

    for (_ <- 0 until testTime) {
      val arr1 = generateRandomArray(maxSize, maxValue)
      val arr2 = copyArray(arr1)

      selectionSort(arr1)
      comparator(arr2)
    }

    println(if (succeed) "Nice!" else "Fucking fucked!")
  }
}