; for some reason things break when there is no comment here! CHECK THE SCANNER!
(define proc
  (lambda (a b)
    (let ((sum (+ a (func b))))
      sum)))