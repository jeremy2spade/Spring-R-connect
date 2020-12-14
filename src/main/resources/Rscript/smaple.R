install.packages('ggplot2')


library(ggplot2)

#df <- data.frame(years = c(1950, 1960, 1970, 1980, 1990, 2000, 2010),  
#                 gdp = c(300.2, 543.3, 1075.9, 2862.5, 5979.6, 10289.7, 14958.3))

CSVdata <- read.csv("data.csv", header = T)
print(CSVdata)
ggplot(data=CSVdata, aes(x=years, y=gdp)) + #ggplot 틀 생성
geom_line() + #ggplot 틀에 선그래프를 그린다.
geom_point() #ggplot 틀에 데이터 점을 그린다.

write.csv(CSVdata, "result_data.csv")
