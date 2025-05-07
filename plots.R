library(ggplot2)
library(scales)
library(dplyr)

dados <- read.csv("output.csv", stringsAsFactors = FALSE)
colnames(dados) <- c("Algoritmo", "Tempo", "Tamanho", "Tipo_de_Array")

plot_box <- ggplot(dados, aes(x = Algoritmo, y = as.numeric(Tempo) / 1e6)) +
  geom_boxplot(outlier.size = 1) +
  scale_y_log10(
    labels = label_number(accuracy = 1, big.mark = ".", decimal.mark = ",")
  ) +
  labs(
    title = "Tempo de Execução por Algoritmo (ms, escala log)",
    x = "Algoritmo",
    y = "Tempo (ms)"
  ) +
  theme_minimal() +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))

df_media <- dados %>%
  group_by(Algoritmo) %>%
  summarise(TempoMedio = mean(as.numeric(Tempo)))

plot_bar <- ggplot(df_media, aes(x = reorder(Algoritmo, -TempoMedio), y = TempoMedio / 1e6)) +
  geom_col(fill = "steelblue") +
  geom_text(
    aes(label = round(TempoMedio / 1e6, 1)),
    vjust = -0.5, size = 3
  ) +
  scale_y_log10(
    labels = label_number(accuracy = 1, big.mark = ".", decimal.mark = ",")
  ) +
  labs(
    title = "Tempo Médio por Algoritmo",
    x = "Algoritmo",
    y = "Tempo Médio (ms, escala log)"
  ) +
  theme_minimal() +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))

df_media_array <- dados %>%
  group_by(Algoritmo, Tipo_de_Array) %>%
  summarise(TempoMedio = mean(as.numeric(Tempo), na.rm = TRUE))

plot_bar_grouped <- ggplot(df_media_array, aes(x = Algoritmo, y = TempoMedio / 1e3, fill = Tipo_de_Array)) +
  geom_col(position = position_dodge()) +
  geom_text(aes(label = round(TempoMedio / 1e3, 1)), 
            position = position_dodge(width = 0.9), vjust = -0.5, size = 3) +
  scale_y_log10(labels = label_number(accuracy = 1, big.mark = ".", decimal.mark = ",")) +
  labs(
    title = "Tempo Médio por Algoritmo e Tipo de Array",
    x = "Algoritmo",
    y = "Tempo Médio (µs, escala log)",
    fill = "Tipo de Array"
  ) +
  theme_minimal() +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))

jpeg("boxplot_algoritmos.jpeg", width = 800, height = 600, res = 100)
print(plot_box)
dev.off()

jpeg("barras_tempo_medio.jpeg", width = 800, height = 600, res = 100)
print(plot_bar)
dev.off()

jpeg("barras_por_tipo_array.jpeg", width = 800, height = 600, res = 100)
print(plot_bar_grouped)
dev.off()
