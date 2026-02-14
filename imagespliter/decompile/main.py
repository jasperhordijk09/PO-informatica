from typing import Tuple
import cv2
import numpy as np
import pathlib
Image = Tuple[Tuple[int, int], Tuple[int, int]]
def process_image(imagePath):
    pathlib.Path("result").mkdir(parents=True,exist_ok=True)

    imagePath = pathlib.Path(imagePath)
    if not imagePath.exists() or not imagePath.is_file():
        print("Error opening image")
        exit(1)
    print(imagePath,imagePath.stem)
    image = cv2.imread(str(imagePath),cv2.IMREAD_UNCHANGED)
    if image is None:
        print("Error opening image")
        exit(1)
    imageH, imageW, _ = image.shape
    bgcolor = image[0,0]
    print(f"bgcolor: {bgcolor}")
    images:list[Image] = []
    y = 1
    while y < imageH:
        x = 1
        inImage = False
        currentImage:Image = ((-1,-1),(-1,-1))
        currentRow:list[Image] = []
        while x < imageW:
            if image[y,x][0] != bgcolor[0] or image[y,x][1] != bgcolor[1] or image[y,x][2] != bgcolor[2]:
                if not inImage:
                    print(f"image starts at x={x} y={y}")
                    currentImage = ((x,y),currentImage[1])
                    inImage = True
            else:
                if inImage:
                    currentImage = (currentImage[0],(x,y))
                    currentRow.append(currentImage)
                    print(f"image ends at x={x} y={y}")
                    inImage = False
            x += 1
        yStart = y
        while image[y,1][0] != bgcolor[0] or image[y,1][1] != bgcolor[1] or image[y,1][2] != bgcolor[2]:
            y += 1
            if y >= imageH:
                break
        for i in currentRow:
            images.append((i[0],(i[1][0],y)))
        while image[y,1][0] == bgcolor[0] and image[y,1][1] == bgcolor[1] and image[y,1][2] == bgcolor[2]:
            y += 1
            if y >= imageH:
                break
    for i, img in enumerate(images):
        currentImageCv2 = image[img[0][1]:img[1][1],img[0][0]:img[1][0]].copy()
        currentImageCv2Mirror = cv2.flip(currentImageCv2, 1)
        print(currentImageCv2.shape)
        cv2.imwrite(f"result/image_{i}.png", currentImageCv2)
        cv2.imwrite(f"result/image_{i}_mirror.png", currentImageCv2Mirror)
        
    
process_image("image.png")