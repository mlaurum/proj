;;; Test cases for Scheme.
;;;
;;; In order to run only a prefix of these examples, add the line
;;;
;;; (exit)
;;;
;;; after the last test you wish to run.

;;; **********************************
;;; *** Add more of your own here! ***

;;;Problem 3

*
; expect #[*]

7
; expect 7

number?
;expect #[number?]

;;;Problem 4

(/ 1 0)
; expect Error

(/ 0 1)
; expect 0

(+ (* 72 42) (- (* 8 37)) (- (* 41 3) 836))
; expect 2015

(number? 5)
; expect True

(number? 'a)
; expect False

(+ True False)
; expect Error

(integer? 3.7932697)
; expect False

;;;Problem 5A

(define mnadsfbi33wkjn 5)
;expect mnadsfbi33wkjn

mnadsfbi33wkjn
;expect 5

(define define 4)
;expect define

define
;expect 4

(define 1 5)
;expect Error

(define denero (define gh 'dennn_ea_ro))
;expect denero

denero
;expect gh

gh
;expect dennn_ea_ro

;;;Problem 6B

'denero
; expect denero

(quote (7 (1 . (3 . (4 . hello)))))
; expect (7 (1 3 4 . hello))

(cdr '(a b c))
; expect (b c)

(eval (cons 'cdr '('(5 3 8))))
; expect (3 8)

'
; expect

(quote ')
; expect Error

;;;Problem 7

(begin ( + a 5 3))
;expect Error 

(begin (begin (+ 5 3)) (- 4 3))
;expect 1

(begin (print 'print) (+ 5 3) '(* 3 2))
;expect print ;(* 3 2)

(define g (begin (print 1) (lambda (x) (+ x 1))))
;expect 1; g

(g 2)
;expect 3

;;;Problem 8

(define tree (lambda (x y) (+ x y) (- x y) (* x y)))
; expect tree

tree
; expect (lambda (x y) (+ x y) (- x y) (* x y))

(tree 4 5)
; expect 20

(define test (lambda (x y z) (/ x y z)))
(test 3 0 1)
; expect Error

(define meep (lambda (x y) (a b)))
(meep 3 5)
; expect Error

;;;Problem 9A

(define (the x) (* 18 x))
;expect the

(the 3)
;expect 54

(define (stanford x) 
          (lambda (y) (* x y)))
;expect stanford

((stanford 4) 2)
;expect 8

(define (who are we) (* we are))
;expect who

(who 3 8)
;expect 24

;;;Problem 10

(define (bear go)
  (cons go (cons 'bears nil))))
(bear 'go)
; expect (go bears)

(define (roll c a l)
  (cons 'roll (cons c (cons a (cons l nil)))))
(roll 'on 'you 'bears)
; expect (roll on you bears)

;;;Problem 11B

(define (this is bear territory)
  (cons 'this (cons is (cons bear (cons territory nil)))))
(this 'is 'not 'sparta)
; expect (this is not sparta)

(this 'is 'cal)
; expect Error

(this 'is 'the 'number 'one 'public 'university 'in 'the 'world)
; expect Error

;;;Problem 12

(define (chop that tree)
  (cons 'block (cons 'that (cons 'punt nil))))
(chop 'it 'down)
; expect (block that punt)

(define (save our gpa)
  (append '(please give me a) (list gpa)))
(save 'my 4.0)
; expect (please give me a 4.0)

;;;Problem 13

(if (eq? 'merry 'christmas) (quote tis not) 'christmas)
;expect christmas

(if (not (eq? 'merry 'christmas)) '(tis not) 'christmas)
;expect (tis not)

(if (= 42 42) 'life_meaning)
;expect life_meaning

(if (eq? 'meaning_of_life 52) 'no 'no_no_no)
;expect no_no_no

(if (eq? 'heart_of_gold 'arthur) 'trillian)
;expect okay

;;;Problem 14B

(and 27 'a 'wrong False)
; expect False

(and 1)
; expect 1

(and 'failing 1.0 (+ 'study 'harder))
; expect Error

(and (- 300) (define a 5) (or 3 False))
; expect 3

(and 404 Error)
; expect #[error]

(or 0 Error)
; expect 0

(or (/ 1 9801) 'no 'stop)
; expect 0.00010203040506070809

(and 'say (or (and (or False (and 3 5 27)) 0 'why False) 'please))
; expect please

;;;Problem 15A

(cond 
    ((= (/ 1 0) 4) 'read_mee)
    (else 'not))
;expect Error

(cond
    ((= 4 2) 'tack)
    ((eq? 'orn 'Tiatn) 'erin)
    (else (cons 'answer (cons '= (cons 00101010 nil)))))
;expect (answer = 101010)

(define handsome 'jack)
(cond 
    ((eq? 'jack handsome) 'very 'yet_veni 'vidi 'victum)
    (else 'hells_road_paved_with_good_intentions))
;expect victum

(cond 
    ((= 42 7) 'ya_no)
    ((= 42 42))
    (else 'nassss))
;expect True

;;;Problem 16

(define challenge 9000)
(define accepted 1)
(let ((challenge 2000) (accepted 15)) (+ challenge accepted))
; expect 2015

(+ challenge accepted)
; expect 9001

(define (floor_1 dinos)
  (cons 'cant (cons dinos (cons 'wont (cons dinos nil)))))
(define kkf 'stop)
(let ((kkf 'fail)) (floor_1 kkf))
; expect (cant fail wont fail)

(floor_1 kkf)
; expect (cant stop wont stop)

;;;Problem 17

(define cow (mu (moo) (append (list moo) (list tank))))
(define miltank (lambda (mil tank) (cow mil)))
(miltank 'mooooooooooo 'imacow)
; expect (mooooooooooo imacow)

;;; **********************************
;;; ******Part Three Test Cases*******
;;; **********************************

(define (caar x) (car (car x)))
(define (cadr x) (car (cdr x)))
(define (cddr x) (cdr (cdr x)))
(define (cadar x) (car (cdr (car x))))

; Some utility functions that you may find useful to implement.
(define (map proc items)
  (cond
    ((null? items) nil)
    (else (cons (proc (car items)) (map proc (cdr items))))
  )
)

(define (cons-all first rests)
  (cond
    ((null? rests) nil)
    (else (cons (append (list first) (car rests)) (cons-all first (cdr rests))))
  )
)

(define (zip pairs)
  (define lst1 (map car pairs))
  (define lst2 (map cadr pairs))
  (list lst1 lst2))

;; Problem 18
;; Returns a list of two-element lists
(define (enumerate s)
  ; BEGIN Question 18
  (define (helper s index)
    (if (null? s) nil
      (cons (cons index (cons (car s) nil)) (helper (cdr s) (+ 1 index)))
    ))
  (helper s 0)
)
  ; END Question 18

;; Problem 19
;; List all ways to make change for TOTAL with DENOMS
(define (list-change total denoms)
  ; BEGIN Question 19
    (cond
      ((null? denoms) nil)
      ((< total 0) nil)
      ((< total (car denoms)) (list-change total (cdr denoms)))  ;first denom is too big - take it out
      ((= total (car denoms)) (cons (cons (car denoms) nil) (list-change total (cdr denoms))))
      (else
        (append (cons-all (car denoms) (list-change (- total (car denoms)) denoms)) ;with first denom
                                      (list-change total (cdr denoms)))   ;without first denom
      )
    )  
   )    
  ; END Question 19
)

;; Problem 20
;; Returns a function that checks if an expression is the special form FORM
(define (check-special form)
  (lambda (expr) (equal? form (car expr))))

(define lambda? (check-special 'lambda))
(define define? (check-special 'define))
(define quoted? (check-special 'quote))
(define let?    (check-special 'let))

;; Converts all let special forms in EXPR into equivalent forms using lambda
(define (analyze expr)
  (cond ((atom? expr)
         ; BEGIN Question 20
         expr
         ; END Question 20
         )
        ((quoted? expr)
         ; BEGIN Question 20
         expr
         ; END Question 20
         )
        ((or (lambda? expr)
             (define? expr))
         (let ((form   (car expr))
               (params (cadr expr))
               (body   (cddr expr)))
           ; BEGIN Question 20
          (cons form (cons params (map analyze body)))
           ; END Question 20
           ))
        ((let? expr)
         (let ((values (cadr expr))
               (body   (cddr expr)))
           ; BEGIN Question 20
           (define parameters (car (zip values)))
           (define input (cadr (zip values)))
           (cons (cons 'lambda (cons (map analyze parameters) (map analyze body))) (map analyze input))
           ; END Question 20
           ))
        (else
         ; BEGIN Question 20
         (map analyze expr)
         ; END Question 20
         )))

;;;Helpers

(cons-all 1 '((1 2) (3 2) (1 4)))
;expect ((1 1 2) (1 3 2) (1 1 4))

(cons-all 2 '((1 2 (1)) (1 3 4 (1 2))))
;expect ((2 1 2 (1)) (2 1 3 4 (1 2)))

(zip '((1 2) (4 2) (4 1)))
;expect ((1 4 4) (2 2 1))

;;;Question 18

(enumerate '(1 2 3 4))
;expect ((0 1) (1 2) (2 3) (3 4))

(enumerate '())
;expect ()

(enumerate '(borderlands is the best))
;expect ((0 borderlands) (1 is) (2 the) (3 best))

;;;Question 19

(list-change 42 '(42 7))
;expect ((42) (7 7 7 7 7 7))

(list-change 4 '((/ 1 0)))
;expect Error

(list-change 5 '(5 2 1))
;expect ((5) (2 2 1) (2 1 1 1) (1 1 1 1 1))

;;;Question 20

(analyze 37)
; expect 37

(analyze 'berkeley)
; expect berkeley

(analyze '(/ 1 0))
; expect (/ 1 0)

(analyze 'Error)
; expect error

(analyze Error)
; expect Error

(analyze '(quote (analyze (let ((c 3) (a 5) (l 7)) (/ c a l)))))
; expect (quote (analyze (let ((c 3) (a 5) (l 7)) (/ c a l))))

(analyze '(lambda (go bears) (* go bears)))
; expect (lambda (go bears) (* go bears))

(analyze '(lambda ((let ((a 3) (b 5)) (+ a b))) ((lambda (x) (< x 1997)))))
; expect (lambda ((let ((a 3) (b 5)) (+ a b))) ((lambda (x) (< x 1997))))

(analyze '(lambda (harry potter) 
    (let ((harry expecto) (potter patronum)) (append harry potter))))
; expect (lambda (harry potter) ((lambda (harry potter) (append harry potter)) expecto patronum))

(analyze '(let ((beat 2000) (stanford 15)) (+ beat stanford)))
; expect ((lambda (beat stanford) (+ beat stanford)) 2000 15)

(analyze '(let ((u 10) (c 20)) (let ((go 30) (cal 40)) (/ go cal))))
; analyze ((lambda (u c) ((lambda (go cal) (/ go cal)) 30 40)) 10 20)

;;;*******************************

;;; These are examples from several sections of "The Structure
;;; and Interpretation of Computer Programs" by Abelson and Sussman.

;;; License: Creative Commons share alike with attribution

;;; 1.1.1

10
; expect 10

(+ 137 349)
; expect 486

(- 1000 334)
; expect 666

(* 5 99)
; expect 495

(/ 10 5)
; expect 2

(+ 2.7 10)
; expect 12.7

(+ 21 35 12 7)
; expect 75

(* 25 4 12)
; expect 1200

(+ (* 3 5) (- 10 6))
; expect 19

(+ (* 3 (+ (* 2 4) (+ 3 5))) (+ (- 10 7) 6))
; expect 57

(+ (* 3
      (+ (* 2 4)
         (+ 3 5)))
   (+ (- 10 7)
      6))
; expect 57


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Move the following (exit) line to run additional tests. ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;; 1.1.2

(define size 2)
; expect size
size
; expect 2

(* 5 size)
; expect 10

(define pi 3.14159)
(define radius 10)
(* pi (* radius radius))
; expect 314.159

(define circumference (* 2 pi radius))
circumference
; expect 62.8318

;;; 1.1.4

(define (square x) (* x x))
; expect square
(square 21)
; expect 441

(define square (lambda (x) (* x x))) ; See Section 1.3.2
(square 21)
; expect 441

(square (+ 2 5))
; expect 49

(square (square 3))
; expect 81

(define (sum-of-squares x y)
  (+ (square x) (square y)))
(sum-of-squares 3 4)
; expect 25

(define (f a)
  (sum-of-squares (+ a 1) (* a 2)))
(f 5)
; expect 136

;;; 1.1.6

(define (abs x)
  (cond ((> x 0) x)
        ((= x 0) 0)
        ((< x 0) (- x))))
(abs -3)
; expect 3

(abs 0)
; expect 0

(abs 3)
; expect 3

(define (a-plus-abs-b a b)
  ((if (> b 0) + -) a b))
(a-plus-abs-b 3 -2)
; expect 5

;;; 1.1.7

(define (sqrt-iter guess x)
  (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x)
                 x)))
(define (improve guess x)
  (average guess (/ x guess)))
(define (average x y)
  (/ (+ x y) 2))
(define (good-enough? guess x)
  (< (abs (- (square guess) x)) 0.001))
(define (sqrt x)
  (sqrt-iter 1.0 x))
(sqrt 9)
; expect 3.00009155413138

(sqrt (+ 100 37))
; expect 11.704699917758145

(sqrt (+ (sqrt 2) (sqrt 3)))
; expect 1.7739279023207892

(square (sqrt 1000))
; expect 1000.000369924366

;;; 1.1.8

(define (sqrt x)
  (define (good-enough? guess)
    (< (abs (- (square guess) x)) 0.001))
  (define (improve guess)
    (average guess (/ x guess)))
  (define (sqrt-iter guess)
    (if (good-enough? guess)
        guess
        (sqrt-iter (improve guess))))
  (sqrt-iter 1.0))
(sqrt 9)
; expect 3.00009155413138

(sqrt (+ 100 37))
; expect 11.704699917758145

(sqrt (+ (sqrt 2) (sqrt 3)))
; expect 1.7739279023207892

(square (sqrt 1000))
; expect 1000.000369924366

;;; 1.3.1

(define (cube x) (* x x x))
(define (sum term a next b)
  (if (> a b)
      0
      (+ (term a)
         (sum term (next a) next b))))
(define (inc n) (+ n 1))
(define (sum-cubes a b)
  (sum cube a inc b))
(sum-cubes 1 10)
; expect 3025

(define (identity x) x)
(define (sum-integers a b)
  (sum identity a inc b))
(sum-integers 1 10)
; expect 55

;;; 1.3.2

((lambda (x y z) (+ x y (square z))) 1 2 3)
; expect 12

(define (f x y)
  (let ((a (+ 1 (* x y)))
        (b (- 1 y)))
    (+ (* x (square a))
       (* y b)
       (* a b))))
(f 3 4)
; expect 456

(define x 5)
(+ (let ((x 3))
     (+ x (* x 10)))
   x)
; expect 38

(let ((x 3)
      (y (+ x 2)))
  (* x y))
; expect 21

;;; 2.1.1

(define (add-rat x y)
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(define (sub-rat x y)
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))
(define (mul-rat x y)
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))
(define (div-rat x y)
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))
(define (equal-rat? x y)
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))

(define x (cons 1 2))
(car x)
; expect 1

(cdr x)
; expect 2

(define x (cons 1 2))
(define y (cons 3 4))
(define z (cons x y))
(car (car z))
; expect 1

(car (cdr z))
; expect 3

z
; expect ((1 . 2) 3 . 4)

(define (make-rat n d) (cons n d))
(define (numer x) (car x))
(define (denom x) (cdr x))
(define (print-rat x)
  (display (numer x))
  (display '/)
  (display (denom x))
  (newline))
(define one-half (make-rat 1 2))
(print-rat one-half)
; expect 1/2 ; okay

(define one-third (make-rat 1 3))
(print-rat (add-rat one-half one-third))
; expect 5/6 ; okay

(print-rat (mul-rat one-half one-third))
; expect 1/6 ; okay

(print-rat (add-rat one-third one-third))
; expect 6/9 ; okay

(define (gcd a b)
  (if (= b 0)
      a
      (gcd b (remainder a b))))
(define (make-rat n d)
  (let ((g (gcd n d)))
    (cons (/ n g) (/ d g))))
(print-rat (add-rat one-third one-third))
; expect 2/3 ; okay

(define one-through-four (list 1 2 3 4))
one-through-four
; expect (1 2 3 4)

(car one-through-four)
; expect 1

(cdr one-through-four)
; expect (2 3 4)

(car (cdr one-through-four))
; expect 2

(cons 10 one-through-four)
; expect (10 1 2 3 4)

(cons 5 one-through-four)
; expect (5 1 2 3 4)

(define (map proc items)
  (if (null? items)
      nil
      (cons (proc (car items))
            (map proc (cdr items)))))
(map abs (list -10 2.5 -11.6 17))
; expect (10 2.5 11.6 17)

(map (lambda (x) (* x x))
     (list 1 2 3 4))
; expect (1 4 9 16)

(define (scale-list items factor)
  (map (lambda (x) (* x factor))
       items))
(scale-list (list 1 2 3 4 5) 10)
; expect (10 20 30 40 50)

(define (count-leaves x)
  (cond ((null? x) 0)
        ((not (pair? x)) 1)
        (else (+ (count-leaves (car x))
                 (count-leaves (cdr x))))))
(define x (cons (list 1 2) (list 3 4)))
(count-leaves x)
; expect 4

(count-leaves (list x x))
; expect 8

;;; 2.2.3

(define (odd? x) (= 1 (remainder x 2)))
(define (filter predicate sequence)
  (cond ((null? sequence) nil)
        ((predicate (car sequence))
         (cons (car sequence)
               (filter predicate (cdr sequence))))
        (else (filter predicate (cdr sequence)))))
(filter odd? (list 1 2 3 4 5))
; expect (1 3 5)

(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))
(accumulate + 0 (list 1 2 3 4 5))
; expect 15

(accumulate * 1 (list 1 2 3 4 5))
; expect 120

(accumulate cons nil (list 1 2 3 4 5))
; expect (1 2 3 4 5)

(define (enumerate-interval low high)
  (if (> low high)
      nil
      (cons low (enumerate-interval (+ low 1) high))))
(enumerate-interval 2 7)
; expect (2 3 4 5 6 7)

(define (enumerate-tree tree)
  (cond ((null? tree) nil)
        ((not (pair? tree)) (list tree))
        (else (append (enumerate-tree (car tree))
                      (enumerate-tree (cdr tree))))))
(enumerate-tree (list 1 (list 2 (list 3 4)) 5))
; expect (1 2 3 4 5)

;;; 2.3.1

(define a 1)

(define b 2)

(list a b)
; expect (1 2)

(list 'a 'b)
; expect (a b)

(list 'a b)
; expect (a 2)

(car '(a b c))
; expect a

(cdr '(a b c))
; expect (b c)

(define (memq item x)
  (cond ((null? x) false)
        ((eq? item (car x)) x)
        (else (memq item (cdr x)))))
(memq 'apple '(pear banana prune))
; expect False

(memq 'apple '(x (apple sauce) y apple pear))
; expect (apple pear)

(define (equal? x y)
  (cond ((pair? x) (and (pair? y)
                        (equal? (car x) (car y))
                        (equal? (cdr x) (cdr y))))
        ((null? x) (null? y))
        (else (eq? x y))))
(equal? '(1 2 (three)) '(1 2 (three)))
; expect True

(equal? '(1 2 (three)) '(1 2 three))
; expect False

(equal? '(1 2 three) '(1 2 (three)))
; expect False

;;; Peter Norvig tests (http://norvig.com/lispy2.html)

(define double (lambda (x) (* 2 x)))
(double 5)
; expect 10

(define compose (lambda (f g) (lambda (x) (f (g x)))))
((compose list double) 5)
; expect (10)

(define apply-twice (lambda (f) (compose f f)))
((apply-twice double) 5)
; expect 20

((apply-twice (apply-twice double)) 5)
; expect 80

(define fact (lambda (n) (if (<= n 1) 1 (* n (fact (- n 1))))))
(fact 3)
; expect 6

(fact 50)
; expect 30414093201713378043612608166064768844377641568960512000000000000

(define (combine f)
  (lambda (x y)
    (if (null? x) nil
      (f (list (car x) (car y))
         ((combine f) (cdr x) (cdr y))))))
(define zip (combine cons))
(zip (list 1 2 3 4) (list 5 6 7 8))
; expect ((1 5) (2 6) (3 7) (4 8))

(define riff-shuffle (lambda (deck) (begin
    (define take (lambda (n seq) (if (<= n 0) (quote ()) (cons (car seq) (take (- n 1) (cdr seq))))))
    (define drop (lambda (n seq) (if (<= n 0) seq (drop (- n 1) (cdr seq)))))
    (define mid (lambda (seq) (/ (length seq) 2)))
    ((combine append) (take (mid deck) deck) (drop (mid deck) deck)))))
(riff-shuffle (list 1 2 3 4 5 6 7 8))
; expect (1 5 2 6 3 7 4 8)

((apply-twice riff-shuffle) (list 1 2 3 4 5 6 7 8))
; expect (1 3 5 7 2 4 6 8)

(riff-shuffle (riff-shuffle (riff-shuffle (list 1 2 3 4 5 6 7 8))))
; expect (1 2 3 4 5 6 7 8)

;;; Additional tests

(apply square '(2))
; expect 4

(apply + '(1 2 3 4))
; expect 10

(apply (if false + append) '((1 2) (3 4)))
; expect (1 2 3 4)

(if 0 1 2)
; expect 1

(if '() 1 2)
; expect 1

(or false true)
; expect True

(or)
; expect False

(and)
; expect True

(or 1 2 3)
; expect 1

(and 1 2 3)
; expect 3

(and False (/ 1 0))
; expect False

(and True (/ 1 0))
; expect Error

(or 3 (/ 1 0))
; expect 3

(or False (/ 1 0))
; expect Error

(or (quote hello) (quote world))
; expect hello

(if nil 1 2)
; expect 1

(if 0 1 2)
; expect 1

(if (or false False #f) 1 2)
; expect 2

(define (loop) (loop))
(cond (false (loop))
      (12))
; expect 12

((lambda (x) (display x) (newline) x) 2)
; expect 2 ; 2

(define g (mu () x))
(define (high f x)
  (f))

(high g 2)
; expect 2

(define (print-and-square x)
  (print x)
  (square x))
(print-and-square 12)
; expect 12 ; 144

(/ 1 0)
; expect Error

(define addx (mu (x) (+ x y)))
(define add2xy (lambda (x y) (addx (+ x x))))
(add2xy 3 7)
; expect 13


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Scheme Implementations ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; len outputs the length of list s
(define (len s)
  (if (eq? s '())
    0
    (+ 1 (len (cdr s)))))
(len '(1 2 3 4))
; expect 4


;;;;;;;;;;;;;;;;;;;;
;;; Extra credit ;;;
;;;;;;;;;;;;;;;;;;;;

(exit)

; Tail call optimization tests

(define (sum n total)
  (if (zero? n) total
    (sum (- n 1) (+ n total))))
(sum 1001 0)
; expect 501501

(define (sum n total)
  (cond ((zero? n) total)
        (else (sum (- n 1) (+ n total)))))
(sum 1001 0)
; expect 501501

(define (sum n total)
  (begin 2 3
    (if (zero? n) total
      (and 2 3
        (or false
          (begin 2 3
            (let ((m n))
              (sum (- m 1) (+ m total)))))))))
(sum 1001 0)
; expect 501501

