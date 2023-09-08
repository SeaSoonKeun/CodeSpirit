import time


# 练手
def bubble_sort(arr):
    if arr is None or len(arr) < 2:
        return

    # [0, len(arr) -1)
    for i in range(len(arr)):
        is_swapped = False
        for j in range(len(arr) - 1, 0, -1):
            if arr[j] < arr[j-1]:
                swap(arr, j, j-1)
                is_swapped = True
            if not is_swapped:
                break

def swap(arr, i, j):
    """
    Swaps the elements at the given indices in the list.

    Parameters:
    arr (list): The list in which the elements need to be swapped.
    i (int): The index of the first element.
    j (int): The index of the second element.
    """
    arr[i], arr[j] = arr[j], arr[i]


def comparator(arr):
    """
    Sorts the given list using the built-in sort function for comparison.

    Parameters:
    arr (list): The list to be sorted.
    """
    arr.sort()


def generate_random_array(max_size, max_value):
    """
    Generates a random list of integers.

    Parameters:
    max_size (int): The maximum size of the list.
    max_value (int): The maximum value of the integers in the list.

    Returns:
    list: A randomly generated list of integers.
    """
    import random
    arr = [random.randint(-max_value, max_value) for _ in range(random.randint(0, max_size))]
    return arr


def copy_array(arr):
    """
    Creates a copy of the given list.

    Parameters:
    arr (list): The list to be copied.

    Returns:
    list: A copy of the original list.
    """
    return arr.copy()


def is_equal(arr1, arr2):
    """
    Checks if two lists are equal.

    Parameters:
    arr1 (list): The first list.
    arr2 (list): The second list.

    Returns:
    bool: True if the lists are equal, False otherwise.
    """
    return arr1 == arr2


def print_array(arr):
    """
    Prints the elements of the list.

    Parameters:
    arr (list): The list to be printed.
    """
    print(" ".join(map(str, arr)))


def main():
    start_time = time.time()
    test_time = 500000
    max_size = 100
    max_value = 100
    succeed = True
    # _的含义是
    for _ in range(test_time):
        arr1 = generate_random_array(max_size, max_value)
        arr2 = copy_array(arr1)
        bubble_sort(arr1)
        comparator(arr2)
        if not is_equal(arr1, arr2):
            succeed = False
            print_array(arr1)
            print_array(arr2)
            break
    print("Nice!" if succeed else "Fucking fucked!")
    end_time = time.time()
    execution_time = end_time - start_time

    print(f"执行时间：{execution_time}秒")


if __name__ == "__main__":
    main()
