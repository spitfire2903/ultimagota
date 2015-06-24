#!/bin/bash 
#for arq in 'find /Users/ricardomiranda/AndroidStudioProjects/GotaDAgua -name "*.java"'; do
#tmp='wc -l $arq | awk &acute;{print $1}&acute;'
#num='expr $num + $tmp'
#done

#for line in $(cat files); do echo "$line" ; done 

# oldIFS=$IFS  # backup do separador de campo 
# IFS=$'n'     # novo separador de campo, o caractere de fim de linha 
# num=0
# for ligne in $(cat files) 
# do 
#    tmp="wc -l $ligne"
#    num=$num + $tmp"
# done 
# IFS=$old_IFS

# echo Total: $num

# find .  -name "*.java" -print

# arq=(find . -name "*.java")
# wc -l $arq

while IFS='' read -r line || [[ -n $line ]]; do
    tmp=$(wc -l $line)
    echo $tmp
done < "$1"