clear

figure;

x = 0:0.5:50;
y = x.^2;
L = length(y);
y1 = 0:(L - 1);


for i = 1:length(y)
  y1(i) = y(i) + randi([-1000, 1000]);
end

y2 = movmean(y1, 8)

y3 = movmean(y2, 8)

y4 = movmean(y3, 8)

y5 = movmean(y4, 8)

y6 = movmean(y5, 8)

y7 = movmean(y6, 8)

subplot(2,4,1)
plot(x,y);
xlabel ("X");
ylabel("Y");
title("Graph of the original function");

subplot(2,4,2)
plot(x, y1)
xlabel ("X");
ylabel("Y");
title("Graph of the salted function");

subplot(2,4,3)
plot(x, y2)
xlabel ("X");
ylabel("Y");
title("Graph of the first smoothed function");

subplot(2,4,4)
plot(x, y3)
xlabel ("X");
ylabel("Y");
title("Graph of the second smoothed function");

subplot(2,4,5)
plot(x, y4)
xlabel ("X");
ylabel("Y");
title("Graph of the third smoothed function");

subplot(2,4,6)
plot(x, y5)
xlabel ("X");
ylabel("Y");
title("Graph of the fourth smoothed function");

subplot(2,4,7)
plot(x, y6)
xlabel ("X");
ylabel("Y");
title("Graph of the fifth smoothed function");

subplot(2,4,8)
plot(x, y7)
xlabel ("X");
ylabel("Y");
title("Graph of the sixth smoothed function");
