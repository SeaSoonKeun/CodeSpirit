#!/bin/bash

# 练手
bubbleSort() {
  local -a arr=$1
  local len=${#arr[@]}
  if [[ $len -lt 2 ]]; then
    return 0
  fi

  for ((i = 0; i < $len; i++)); do
    local -a isSwapped=0
    for ((j = $len - 1; j > i; j--)); do
      if ((arr[j] < arr[j - 1])); then
        swap arr $j $((j - 1))
        isSwapped=1
      fi
    done
    if ((isSwapped=0)); then
      break
    fi
  done
}

swap() {
  local -a arr=$1
  local i=$2
  local j=$3
  local temp=${arr[$i]}
  arr[$i]=${arr[$j]}
  arr[$j]=$temp
}

comparator() {
  local -a arr=$1
  arr=($(printf "%s\n" "${arr[@]}" | sort -n))
}

generateRandomArray() {
  local maxSize=$1
  local maxValue=$2
  local arr=()
  for ((i = 0; i < $(($RANDOM % maxSize)); i++)); do
    # 通过对 (2 * maxValue + 1) 取模，我们可以得到 0 到 (2 * maxValue) 之间的余数。
    # 最后，通过将余数减去 maxValue，我们将范围转换为 (-maxValue) 到 maxValue。
    # 综上所述，对 (2 * maxValue + 1) 取模的目的是确保生成的随机数在 -maxValue 到 maxValue 之间，包括 maxValue。
    arr+=($(($RANDOM % (2 * maxValue + 1) - maxValue)))
  done
  echo "${arr[@]}"
}

copyArray() {
  local -a arr=$1
  local copy=("${arr[@]}")
  echo "${copy[@]}"
}

isEqual() {
  #  local -a arr1=$1
  #  local -a arr2=$2
  local -a arr1=("$1")
  local -a arr2=("$2")
  if [[ ${#arr1[@]} -ne ${#arr2[@]} ]]; then
    echo "false"
    return
  fi
  for ((i = 0; i < ${#arr1[@]}; i++)); do
    if [[ ${arr1[$i]} -ne ${arr2[$i]} ]]; then
      echo "false"
      return
    fi
  done
  echo "true"
}

printArray() {
  local -a arr=("$@")
  echo "${arr[@]}"
}

main() {
  start_time=$(date +%s%N) # 获取起始时间

  local testTime=50
  local maxSize=100
  local maxValue=100
  local succeed=true
  for ((i = 0; i < $testTime; i++)); do
    arr1=($(generateRandomArray $maxSize $maxValue))
    arr2=($(copyArray arr1))
    bubbleSort arr1
    comparator arr2
    if [[ $(isEqual arr1 arr2) == "false" ]]; then
      succeed=false
      printArray arr1
      printArray arr2
      break
    fi
  done
  if $succeed; then
    echo "Nice!"
  else
    echo "Fucking fucked!"
  fi
  # 获取结束时间
  end_time=$(date +%s%N)
  # 计算时间差
  duration=$((end_time - start_time))
  echo "代码执行时间: $(($duration / 1000000)) 毫秒"

  ## 示例数组
  #myArray=("apple" "banana" "orange" "grape")
  #
  ## 调用 printArray() 函数打印数组
  #printArray "${myArray[@]}"
}

main
