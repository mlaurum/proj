#!/usr/bin/env python

from __future__ import division
import argparse
import random
import queue


"""
===============================================================================
  Please complete the following function.
===============================================================================
"""

def solve(P, M, N, C, items, constraints):
  """
  Write your amazing algorithm here.

  Return: a list of strings, corresponding to item names.
  """
  #rotate heuristic, logarithmic num of items sampled. 100 iterations
  maxer={}
  for k in range(10):
    itemProfit = 0
    itemWeight = 0
    itemCost = 0
    itemlist = []
    priorityQ = queue.PriorityQueue()
    random.shuffle(items)
    allconstraints = []
    restrictSet = set()

    for i in range(N-1):
      item = items[i]
      heuristic = (item[3]-item[4])
      priorityQ.put((heuristic,item)) #not sure if this queue works
      i = i*2
      
    for i in range(N):
      maxItem = priorityQ.get()[1] 
      allconstraints.append(maxItem[1])
      cond, temp = checkCons(maxItem[1], restrictSet, constraints)
      if cond:
        if itemWeight +maxItem[2] <= P and itemCost + maxItem[3] <= M:
          restrictSet = temp
          itemlist.append(maxItem[0])
          itemWeight += maxItem[2]
          itemCost += maxItem[3]
          itemProfit += maxItem[4]-maxItem[3]
      else:
        allconstraints.remove(maxItem[1])
    maxer[itemProfit] = itemlist
      
  best = max(maxer)
  print (best)
    
  return maxer[best]

def checkCons(lastItemAdded, restrictSet, constraints):
  if lastItemAdded in restrictSet:
    return (False, restrictSet)
  for constraint in constraints:
    if lastItemAdded in constraint:
      for item in constraint:
        if item != lastItemAdded:
          restrictSet.add(item)
  return (True, restrictSet)
      #check set to see if clash


"""
===============================================================================
  No need to change any code below this line.
===============================================================================
"""

def read_input(filename):
  """
  P: float
  M: float
  N: integer
  C: integer
  items: list of tuples
  constraints: list of sets
  """
  with open(filename) as f:
    P = float(f.readline())
    M = float(f.readline())
    N = int(f.readline())
    C = int(f.readline())
    items = []
    constraints = []
    for i in range(N):
      name, cls, weight, cost, val = f.readline().split(";")
      # if(float(cost) > float(val) or float(val) < 0):
      #   continue
      items.append((name, int(cls), float(weight), float(cost), float(val)))
    for i in range(C):
      constraint = set(eval(f.readline()))
      constraints.append(constraint)
  return P, M, N, C, items, constraints

def write_output(filename, items_chosen):
  with open(filename, "w") as f:
    for i in items_chosen:
      f.write("{0}\n".format(i))

if __name__ == "__main__":

  parser = argparse.ArgumentParser(description="PickItems solver.")
  parser.add_argument("input_file", type=str, help="____.in")
  parser.add_argument("output_file", type=str, help="____.out")
  args = parser.parse_args()

  P, M, N, C, items, constraints = read_input(args.input_file)
  items_chosen = solve(P, M, N, C, items, constraints)
  write_output(args.output_file, items_chosen)