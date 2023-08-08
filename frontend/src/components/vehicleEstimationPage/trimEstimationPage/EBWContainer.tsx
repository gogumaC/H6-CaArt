import React, { useContext, useEffect } from 'react';
import styled from 'styled-components';
import EBWButton from './EBWButton';
import { EstimationContext } from '../../../util/Context';

function EBWContainer({
  openSetter,
  typeSetter,
  positionSetter,
}: {
  openSetter: React.Dispatch<React.SetStateAction<boolean>>;
  typeSetter: React.Dispatch<React.SetStateAction<string | undefined>>;
  positionSetter: React.Dispatch<
    React.SetStateAction<{ x: number; y: number }>
  >;
}) {
  const { setEngine, setBody, setWd } = useContext(EstimationContext)!;
  function handleButtonClick(value: string, price: number) {
    switch (value) {
      case '디젤 2.2':
      case '가솔린 3.8':
        setEngine({ name: value, price: price });
        break;

      case '7인승':
      case '8인승':
        setBody({ name: value, price: price });
        break;

      case '2WD':
      case '4WD':
        setWd({ name: value, price: price });
        break;
      default:
        break;
    }
  }

  function calcPosition(target: Element | null | undefined) {
    const rect = target?.getBoundingClientRect() as DOMRect;
    const y = rect.left - 20;
    let x;
    if (target?.innerHTML === '엔진') {
      x = rect.top - 80;
    } else {
      x = rect.top - 100;
    }
    return { x, y };
  }

  function findSpan(e: React.MouseEvent) {
    e.stopPropagation();
    const target = e.target as HTMLElement;
    const spanElement =
      target.parentElement?.parentElement?.previousElementSibling;
    typeSetter(spanElement?.innerHTML);
    const { x, y } = calcPosition(spanElement);
    positionSetter({ x: x, y: y });
    openSetter(true);
  }

  useEffect(() => {
    const spanElement = document.querySelector('.engine');
    const { x, y } = calcPosition(spanElement);
    positionSetter({ x: x, y: y });
    openSetter(true);
  }, []);

  return (
    <>
      <Box className="body-medium-14 text-grey-200">
        <Item>
          <Title className="engine">엔진</Title>
          <ButtonBox onClick={e => findSpan(e)}>
            <EBWButton value="디젤 2.2" price={0} onClick={handleButtonClick} />
            <EBWButton
              value="가솔린 3.8"
              price={1000}
              onClick={handleButtonClick}
            />
          </ButtonBox>
        </Item>
        <Item>
          <Title>바디</Title>
          <ButtonBox onClick={e => findSpan(e)}>
            <EBWButton value="7인승" price={0} onClick={handleButtonClick} />
            <EBWButton value="8인승" price={3000} onClick={handleButtonClick} />
          </ButtonBox>
        </Item>
        <Item>
          <Title>구동방식</Title>
          <ButtonBox onClick={e => findSpan(e)}>
            <EBWButton value="2WD" price={0} onClick={handleButtonClick} />
            <EBWButton value="4WD" price={5000} onClick={handleButtonClick} />
          </ButtonBox>
        </Item>
      </Box>
    </>
  );
}

export default EBWContainer;

const Box = styled.div`
  position: relative;
  width: 309px;
  flex-shrink: 0;
  border-radius: 8px;
  border: 1px solid var(--grey-700);
  padding: 12px;
  display: flex;
  flex-direction: column;
  gap: 12px;
`;

const ButtonBox = styled.div`
  display: flex;
`;

const Title = styled.span`
  margin-bottom: 4px;
`;

const Item = styled.div`
  display: flex;
  flex-direction: column;
`;
